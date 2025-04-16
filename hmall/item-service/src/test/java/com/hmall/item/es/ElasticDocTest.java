package com.hmall.item.es;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hmall.item.domain.po.Item;
import com.hmall.item.domain.po.ItemDoc;
import com.hmall.item.service.IItemService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(properties = "spring.profiles.active=local")
public class ElasticDocTest {

    private RestHighLevelClient client;

    @Autowired
    private IItemService itemService;

    @Test
    void testIndexConnection() throws Exception {
        //0.准备文档数据
        //0.1查询数据库数据
        Item item = itemService.getById(317578L);
        ItemDoc itemDoc = BeanUtil.copyProperties(item, ItemDoc.class);


        IndexRequest request = new IndexRequest("items").id(itemDoc.getId());

        request.source(JSONUtil.toJsonStr(itemDoc), XContentType.JSON);

        client.index(request, RequestOptions.DEFAULT);
    }

    @Test
    void testGetDocById() throws Exception  {
        Item item = itemService.getById(317578L);
        ItemDoc itemDoc = BeanUtil.copyProperties(item, ItemDoc.class);


        GetRequest request = new GetRequest("items", itemDoc.getId());
        GetResponse response = client.get(request, RequestOptions.DEFAULT);

        String sourceAsString = response.getSourceAsString();
        System.out.println("sourceAsString = " + sourceAsString);


    }

    @Test
    void testBulkDoc() throws Exception {
        int pageNo = 1, pageSize = 500;
        while(true){
            Page<Item> page = itemService.lambdaQuery()
                    .eq(Item::getStatus, 1)
                    .page(Page.of(pageNo, pageSize));
            List<Item> records = page.getRecords();
            if(records == null || records.isEmpty()){
                return;
            }

            BulkRequest request = new BulkRequest();

            for (Item item : records) {
                request.add(new IndexRequest("items").id(item.getId().toString()).source(JSONUtil.toJsonStr(BeanUtil.copyProperties(item, ItemDoc.class)),XContentType.JSON));
            }

            client.bulk(request, RequestOptions.DEFAULT);
            //翻页
            pageNo++;
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
