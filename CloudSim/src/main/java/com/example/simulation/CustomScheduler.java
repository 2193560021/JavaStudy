package com.example.simulation;

import org.cloudsimplus.core.SimEntity;
import org.cloudsimplus.core.events.SimEvent;
import org.cloudsimplus.vms.Vm;

import java.util.List;

public class CustomScheduler extends SimEntity implements SchedulingPolicy {
    private List<Vm> vmList;

    public CustomScheduler(String name) {
        super(name);
    }

    @Override
    public void schedule(SimEvent evt) {
        // 自定义调度逻辑（例如：负载均衡）
        for (Vm vm : vmList) {
            // 分配任务到虚拟机
        }
    }

    @Override
    public void cloudletSubmit(SimEvent evt, double ack) {
        // 处理云任务提交事件
    }

    @Override
    public void setVmList(List<Vm> list) {
        this.vmList = list;
    }
}
