package com.atguigu.mybatis.pojo;

public class Dept {

    private int did;

    private String deptName;



    public Dept() {
    }

    public Dept(int did, String deptName) {
        this.did = did;
        this.deptName = deptName;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "did=" + did +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
