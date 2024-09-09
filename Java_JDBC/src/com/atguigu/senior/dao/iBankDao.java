package com.atguigu.senior.dao;

public interface iBankDao {
    public int addMoney(Integer id, Integer money);

    public int subMoney(Integer id, Integer money);
}
