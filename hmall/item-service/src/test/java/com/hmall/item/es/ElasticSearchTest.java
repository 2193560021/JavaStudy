package com.hmall.item.es;

import cn.hutool.json.JSONUtil;
import com.hmall.item.domain.po.ItemDoc;
import com.hmall.item.service.IItemService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest(properties = "spring.profiles.active=local")
public class ElasticSearchTest {

    private RestHighLevelClient client;

    @Autowired
    private IItemService itemService;

    @Test
    void testMatchAll() throws Exception {
        SearchRequest request = new SearchRequest("items");

        request.source()
                .query(QueryBuilders.matchAllQuery());

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println("response = " + response);

        parseRes(response);
    }

    @Test
    void testBoolSearch() throws Exception  {
        SearchRequest request = new SearchRequest("items");

        request.source().query(
                QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("name","脱脂牛奶"))
                        .filter(QueryBuilders.termQuery("brand", "德亚"))
                        .filter(QueryBuilders.rangeQuery("price").lt(30000))
        );

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println("response = " + response);

        parseRes(response);

    }


    @Test
    void testHighlight() throws Exception  {
        int pageNo = 1, pageSize = 5;
        SearchRequest request = new SearchRequest("items");

        request.source().query(QueryBuilders.matchQuery("name", "脱脂牛奶"));

        request.source().highlighter(new HighlightBuilder()
                .field("name")
        );

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println("response = " + response);

        SearchHits searchHits = response.getHits();
        long total = searchHits.getTotalHits().value;

        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            System.out.println(highlightFields.values());
        }

    }


    @Test
    void testSortAndPae() throws Exception  {
        int pageNo = 1, pageSize = 5;
        SearchRequest request = new SearchRequest("items");

        request.source()
                .query(QueryBuilders.matchAllQuery())
                .from((pageNo - 1) * pageSize).size(pageSize)
                .sort("sold", SortOrder.DESC);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println("response = " + response);

        parseRes(response);

    }

    private static void parseRes(SearchResponse response) {
        SearchHits searchHits = response.getHits();
        long total = searchHits.getTotalHits().value;

        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();
            ItemDoc itemDoc = JSONUtil.toBean(json, ItemDoc.class);
            System.out.println(json);
        }
    }


    @BeforeEach
    void setUp() {
        client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.209.128:9200")
        ));
    }

    @AfterEach
    void tearDown() throws Exception {
        if(client != null){
            client.close();
        }
    }

}
