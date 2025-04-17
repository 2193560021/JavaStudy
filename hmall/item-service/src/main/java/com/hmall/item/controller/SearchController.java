package com.hmall.item.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.hmall.common.domain.PageDTO;
import com.hmall.common.utils.BeanUtils;
import com.hmall.item.domain.Constants.ElasticConstants;
import com.hmall.item.domain.dto.ItemDTO;
import com.hmall.item.domain.po.ItemDoc;
import com.hmall.item.domain.query.ItemPageQuery;
import com.hmall.item.service.IItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "搜索相关接口")
@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final IItemService itemService;

    private final RestHighLevelClient client;

    @ApiOperation("搜索商品")
    @GetMapping("/list")
    public PageDTO<ItemDTO> search(ItemPageQuery query) throws Exception {
        // 分页查询
        /*Page<Item> result = itemService.lambdaQuery()
                .like(StrUtil.isNotBlank(query.getKey()), Item::getName, query.getKey())
                .eq(StrUtil.isNotBlank(query.getBrand()), Item::getBrand, query.getBrand())
                .eq(StrUtil.isNotBlank(query.getCategory()), Item::getCategory, query.getCategory())
                .eq(Item::getStatus, 1)
                .between(query.getMaxPrice() != null, Item::getPrice, query.getMinPrice(), query.getMaxPrice())
                .page(query.toMpPage("update_time", false));*/

        //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓更换为使用ElasticSearch查询↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
        //准备request对象
        SearchRequest request = new SearchRequest(ElasticConstants.ITEM_INDEX_NAME);

        //组织DSL语言
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if(StrUtil.isNotBlank(query.getKey())){
            boolQueryBuilder.must(QueryBuilders.matchQuery("name", query.getKey()));
        }
        if(StrUtil.isNotBlank(query.getBrand())){
            boolQueryBuilder.filter(QueryBuilders.termQuery("brand", query.getBrand()));
        }
        if(StrUtil.isNotBlank(query.getCategory())){
            boolQueryBuilder.filter(QueryBuilders.termQuery("category", query.getCategory()));
        }
        if(query.getMaxPrice() != null){
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").lte(query.getMaxPrice()));
        }
        if(query.getMinPrice() != null){
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(query.getMinPrice()));
        }

        request.source().query(boolQueryBuilder);

        //分页
        request.source().from((query.getPageNo() - 1) * query.getPageSize()).size(query.getPageSize());

        //是否排序
        if(StrUtil.isNotBlank(query.getSortBy())){
            request.source().sort(query.getSortBy(), query.getIsAsc() ? SortOrder.ASC : SortOrder.DESC);
        }else {
            request.source().sort("updateTime", query.getIsAsc() ? SortOrder.ASC : SortOrder.DESC);
        }

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        PageDTO<ItemDTO> res = parseRes(response, query);

        // 封装并返回
        return res;
    }


    private static PageDTO<ItemDTO> parseRes(SearchResponse response, ItemPageQuery query) {

        List<ItemDoc> itemDocList = new ArrayList<>();

        SearchHits searchHits = response.getHits();

        //总条数
        long total = searchHits.getTotalHits().value;

        //命中的数据
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();

            ItemDoc itemDoc = JSONUtil.toBean(json, ItemDoc.class);

            itemDocList.add(itemDoc);
        }

        //转为ItemDoc集合
        List<ItemDTO> itemDTOS = BeanUtils.copyToList(itemDocList, ItemDTO.class);
        long pageSize = query.getPageSize().longValue();

        PageDTO<ItemDTO> itemDTOPageDTO = new PageDTO<>(total, pageSize, itemDTOS);

        return itemDTOPageDTO;

    }
}
