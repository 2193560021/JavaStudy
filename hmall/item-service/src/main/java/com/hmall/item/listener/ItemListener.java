package com.hmall.item.listener;

import cn.hutool.json.JSONUtil;
import com.hmall.common.utils.BeanUtils;
import com.hmall.item.domain.Constants.ElasticConstants;
import com.hmall.item.domain.Constants.MQConstants;
import com.hmall.item.domain.dto.ItemDTO;
import com.hmall.item.domain.dto.ItemMQDTO;
import com.hmall.item.domain.po.ItemDoc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class ItemListener {
    private final RestHighLevelClient client;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = MQConstants.DELAY_ORDER_QUEUE_NAME),
            exchange = @Exchange(name = MQConstants.ITEM_EXCHANGE_NAME, delayed = "false"),
            key = MQConstants.ITEM_QUERY_KEY
    ))
    public void listenerItemMessage(ItemMQDTO itemMQDTO){
        if(itemMQDTO.getItemDTO().getId() == null) return;

        switch (itemMQDTO.getItemPerate()){
            case ADD:
                addItemByIndex(itemMQDTO.getItemDTO());
                break;
            case REMOVE:
                removeItemByIndex(itemMQDTO.getItemDTO());
                break;
            case UPDATE:
                updateItemByIndex(itemMQDTO.getItemDTO());
                break;
            default:
                log.error("未知操作类型");
        }
    }


    private void addItemByIndex(ItemDTO item){
        log.info("新增索引库商品：" + item.getId());
        ItemDoc itemDoc = BeanUtils.copyBean(item, ItemDoc.class);
        IndexRequest request = new IndexRequest(ElasticConstants.ITEM_INDEX_NAME).id(item.getId().toString());

        request.source(JSONUtil.toJsonStr(itemDoc), XContentType.JSON);
        try {
            client.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.info("新增索引库商品出错:{}", e.getMessage());
        }
    }

    private void removeItemByIndex(ItemDTO item){
        log.info("移除索引库商品：" + item.getId());
        DeleteRequest request = new DeleteRequest(ElasticConstants.ITEM_INDEX_NAME).id(item.getId().toString());

        try {
            client.delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.info("移除索引库商品出错:{}", e.getMessage());
        }
    }


    private void updateItemByIndex(ItemDTO item){
        log.info("修改索引库商品：" + item.getId());
        UpdateRequest request = new UpdateRequest(ElasticConstants.ITEM_INDEX_NAME, item.getId().toString());

        ItemDoc itemDoc = BeanUtils.copyBean(item, ItemDoc.class);
        itemDoc.setUpdateTime(LocalDateTime.now());
        request.doc(JSONUtil.toJsonStr(itemDoc), XContentType.JSON);
        try {
            client.update(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.info("修改索引库商品出错:{}", e.getMessage());
        }
    }
}
