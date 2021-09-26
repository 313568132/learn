package com.leon.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.leon.excel.vo.ExcelDemo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class ExcelESDemoListener extends AnalysisEventListener<ExcelDemo> {
    @Autowired
    public TransportClient transportClient;
    /**
     * 每隔100条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 10000;
    List<ExcelDemo> list = new ArrayList<>();


    @Override
    public void invoke(ExcelDemo data, AnalysisContext analysisContext) {
        // log.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            disposeData();
            // 存储完成清理 list
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        disposeData();
        log.info("所有数据解析完成！");
    }


    private void disposeData() {
        // 写法1
        String fileName = "C:\\Users\\Administrator\\Desktop\\工作表" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, ExcelDemo.class).sheet("模板").doWrite(data());
    }

    private List<ExcelDemo> data() {

        List<ExcelDemo> excelDemos = new ArrayList<>();
        // list
        int i = 0;

        for (ExcelDemo excelDemo : list) {
            try {
                System.out.println("第" + ++i + excelDemo.getMerchatSku() + "个数据.");
                String join = StringUtils.join(excelDemo.getItemDesc(), "", excelDemo.getItemDescEn());
                QueryBuilder qb = QueryBuilders.matchQuery("descSource" ,QueryParser.escape(join));
                BoolQueryBuilder qb2 = QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("country","CN"))
                        .must(QueryBuilders.matchQuery("mode","B2B"))
                        .must(qb);
                SearchResponse response = transportClient.prepareSearch("product")
                        .setTypes("countryMode").setQuery(qb2).setSize(1)
                        .get(TimeValue.timeValueMillis(100L));
                // log.info("查询ES语句: {}", response.toString());
                SearchHits hits = response.getHits();
                TimeValue took = response.getTook();
                ExcelDemo demo = new ExcelDemo();
                demo.setMerchatSku(excelDemo.getMerchatSku());
                demo.setCategory(excelDemo.getCategory());
                demo.setItemDescEn(excelDemo.getItemDescEn());
                demo.setItemUrl(excelDemo.getItemUrl());
                demo.setItemDesc(excelDemo.getItemDesc());
                demo.setRemark(String.valueOf(took));
                System.out.println("第" + i + "个数据. 查询用时: " + String.valueOf(took));

                for (SearchHit searchHit : hits) {
                    Map<String, Object> source = searchHit.getSource();
                    Object descDeclare = source.get("descDeclare");
                    Object hscode = source.get("hscode");
                    demo.setRemark3(String.valueOf(searchHit.getScore()));
                    demo.setHscode(String.valueOf(hscode));
                    demo.setItemDescDeclear(String.valueOf(descDeclare));
                    System.out.println(source.get("country") + " -----> " + descDeclare);
                }
                excelDemos.add(demo);
            } catch (Exception e) {
                log.error("系统异常!!!!!",e);
            }
        }
        return excelDemos;
    }

}
