package com.atguigu.senior.dao.impl;

import com.atguigu.senior.dao.BaseDAO;
import com.atguigu.senior.dao.iBankDao;

public class BankDaoImpl extends BaseDAO implements iBankDao {

    @Override
    public int addMoney(Integer id, Integer money) {
        try {
            String sql = "update t_bank set money = money + ? where id = ?";
            return executeUpdate(sql,money,id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int subMoney(Integer id, Integer money) {
        try {
            String sql = "update t_bank set money = money - ? where id = ?";
            return executeUpdate(sql,money,id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
