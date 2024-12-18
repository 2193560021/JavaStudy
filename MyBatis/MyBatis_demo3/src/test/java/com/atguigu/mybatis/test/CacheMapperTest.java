package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.CacheMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class CacheMapperTest {
    @Test
    public void getEmpByEidTest(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        Emp empByEid = mapper.getEmpByEid(3);
        System.out.println(empByEid);

        mapper.insertEmp(new Emp(null,"LYY",23,"Man","219356@qq.com",3) );
        Emp empByEid2 = mapper.getEmpByEid(3);
        System.out.println(empByEid2);

    }

    @Test
    public void testTwoCache(){

    }
}
