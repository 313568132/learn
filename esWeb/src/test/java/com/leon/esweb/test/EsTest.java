package com.leon.esweb.test;

import com.alibaba.excel.EasyExcel;
import com.leon.api.vo.ExcelDemo;
import com.leon.esweb.service.ExcelESDemoListener;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Map;

/**
 * @author ：leon xie
 * @date ：2021/1/17 13:34
 */
@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {EsConfig.class, ExcelESDemoListener.class})
public class EsTest {

    @Autowired
    @Qualifier("restHighLevelClient")
    public RestHighLevelClient client;

    @Autowired
    public ExcelESDemoListener excelESDemoListener;

    @Test
    public void isExsitsIndex() throws IOException {
        GetRequest getRequest = new GetRequest("kms_muses_wish_sku", "5f30f138853d5f85fb4874d4#:2647:US:");
        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
    public void es7ExportExcel() {
        String fileName = "C:\\Users\\Administrator\\Desktop\\es7.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, ExcelDemo.class, excelESDemoListener).sheet().doRead();
    }

    @Test
    public void search() throws IOException {
        SearchRequest searchRequest = new SearchRequest("kms_muses_wish_sku");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // searchSourceBuilder.query(QueryBuilders.matchQuery("descSource",QueryParser.escape("description")));
        searchSourceBuilder.fetchSource(null,"descSource");
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .filter(QueryBuilders.termQuery("country","CN"))
                .must(QueryBuilders.matchQuery("descSource",QueryParser.escape("Item Type:Tops\n" +
                        "Tops Type:Tees\n" +
                        "Gender:Men/women/lovers\n" +
                        "Pattern Type:3D Print\n" +
                        "Sleeve Style:conventional\n" +
                        "Brand Name:Gainings\n" +
                        "Occasion: Beach/Casual/Party/Club/Summer\n" +
                        "Style: Fashion Multi Pattern T-shirt Tank Shirts Blouse\n" +
                        "Fabric Type:Broadcloth\n" +
                        "Material: Polyester/Cotton\n" +
                        "Collar:O-Neck\n" +
                        "Sleeve Length:Short\tFashion Original Bulldog Tattoo 3D Print Cotton T-shirt Creative Personality Street Wear Short Sleeves")));
        System.out.println(boolQueryBuilder);
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(3);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest,RequestOptions.DEFAULT);
        System.out.println("响应时间"+ response.getTook());
        SearchHits SearchHits = response.getHits();
        SearchHit[] searchHit = SearchHits.getHits();
        for (SearchHit hit : searchHit) {
            Map<String, Object> map = hit.getSourceAsMap();
            System.out.println(hit.getScore());
            System.out.println(hit.getId());
            System.out.println(hit.getIndex());
            System.out.println(map);
        }
        System.out.println(response.getHits());
    }

}
