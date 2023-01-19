package com.leon.web;

import com.alibaba.excel.EasyExcel;
import com.leon.config.ElasticsearchConfig;
import com.leon.excel.vo.ExcelDemo;
import com.leon.service.ExcelESDemoListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ElasticsearchConfig.class, ExcelESDemoListener.class})
@Slf4j
public class ESClassify {

    @Autowired
    public ExcelESDemoListener excelESDemoListener;
    @Autowired
    public TransportClient transportClient;

    @Test
    public void be(){
        //es测试数据 - 副本.xlsx
        String fileName = "C:\\Users\\Administrator\\Desktop\\es2,es7,tag1-19结果.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, ExcelDemo.class, excelESDemoListener).sheet().doRead();
    }

    @Test
    public void tt(){
        String join = StringUtils.join( "test");
        QueryBuilder qb = QueryBuilders.multiMatchQuery("descSource" ,QueryParser.escape(join));
        BoolQueryBuilder qb2 = QueryBuilders.boolQuery()
                .filter(QueryBuilders.termQuery("country","CN"))
                .filter(QueryBuilders.termQuery("mode","B2B"))
                .must(qb);
        System.out.println(qb2);
    }
}
