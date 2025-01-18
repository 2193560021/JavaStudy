package com.hmdp.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.hmdp.dto.Result;
import com.hmdp.entity.Shop;
import com.hmdp.entity.ShopType;
import com.hmdp.mapper.ShopTypeMapper;
import com.hmdp.service.IShopTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.hmdp.utils.RedisConstants.CACHE_SHOP_KEY;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result queryList() {


        //1.从redis查询商铺缓存

        String shopTypeJson = stringRedisTemplate.opsForValue().get("cache:shopType");

        System.out.println("111shopTypeJson = " + shopTypeJson);

        //2.判断是否存在
        if (StrUtil.isNotBlank(shopTypeJson)) {
            //3.存在，直接返回
            assert shopTypeJson != null;
//            shopTypeJson = shopTypeJson.substring(1, shopTypeJson.length() - 1 );
            System.out.println("222shopTypeJson = " + shopTypeJson);

            List<ShopType> shopType = new ArrayList<>();

            JSONArray objects = JSONUtil.parseArray(shopTypeJson);

//            shopType.addAll(JSONUtil.parseArray(shopTypeJson, ShopType.class)));
            System.out.println("objects = " + objects);
            return Result.ok(objects);
        }

        //4.不存在，查询数据库
        QueryWrapper<ShopType> queryShopType = new QueryWrapper<>();
        queryShopType.getEntity();
        List<ShopType> shopTypeList = list(queryShopType);

        //5.不存在，返回错误
        if (shopTypeList == null) {
            return Result.fail("店铺列表查询错误");
        }

        //6.存在，写入redis
        stringRedisTemplate.opsForValue().set("cache:shopType", JSONUtil.toJsonStr(shopTypeList));
        //7.返回
        return Result.ok(shopTypeList);

    }
}
