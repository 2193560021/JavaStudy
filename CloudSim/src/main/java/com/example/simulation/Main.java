package com.example.simulation;

import org.cloudsimplus.brokers.DatacenterBroker;
import org.cloudsimplus.cloudlets.Cloudlet;
import org.cloudsimplus.datacenters.Datacenter;
import org.cloudsimplus.vms.Vm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // 初始化 CloudSim
            CloudSim.init(1, Calendar.getInstance(), false);

            // 创建数据中心
            Datacenter datacenter = DatacenterConfig.createDatacenter();

            // 创建代理（负责虚拟机和任务的调度）
            DatacenterBroker broker = new DatacenterBroker("Broker");

            // 创建虚拟机列表
            List<Vm> vmList = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                vmList.add(VmConfig.createVm(i));
            }
            broker.createVmInDatacenter(vmList.get(0), datacenter.getId());

            // 创建云任务列表
            List<Cloudlet> cloudletList = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                cloudletList.add(CloudletConfig.createCloudlet(i));
            }
            broker.submitCloudletList(cloudletList);

            // 启动仿真
            CloudSim.startSimulation();
            CloudSim.stopSimulation();

            // 收集结果
            List<Cloudlet> finishedCloudlets = broker.getCloudletFinishedList();
            printResults(finishedCloudlets);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printResults(List<Cloudlet> cloudlets) {
        System.out.println("=== 仿真完成 ===");
        System.out.println("已完成任务数: " + cloudlets.size());
        for (Cloudlet cloudlet : cloudlets) {
            System.out.printf("任务ID=%d, 状态=完成, 耗时=%.2f秒%n",
                    cloudlet.getId(), cloudlet.getActualCPUTime());
        }
    }
}
