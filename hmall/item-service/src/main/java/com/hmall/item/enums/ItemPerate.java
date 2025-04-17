package com.hmall.item.enums;

import lombok.Getter;

@Getter
public enum ItemPerate {
    ADD(0, "新增商品"),
    REMOVE(1, "删除商品"),
    UPDATE(2, "更新商品");

    private final int value;
    private final String desc;

    ItemPerate(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
