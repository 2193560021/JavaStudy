package com.example.simulation;


import org.cloudsimplus.*;
import org.cloudsimplus.allocationpolicies.VmAllocationPolicySimple;
import org.cloudsimplus.datacenters.Datacenter;
import org.cloudsimplus.datacenters.DatacenterCharacteristics;
import org.cloudsimplus.hosts.Host;
import org.cloudsimplus.hosts.HostSimple;
import org.cloudsimplus.schedulers.vm.VmSchedulerTimeShared;

import java.util.*;

/**
 * 数据中心配置
 */
public class DatacenterConfig {
    public static Datacenter createDataCenter(){
        DatacenterCharacteristics dc = new DatacenterCharacteristics(
                "x86", "Linux", List.of("Sun X2100"), 1.0, 1.0, 1.0, 1.0, 1.0
        );

        // 主机列表（物理服务器）
        List<Host> hosts = new ArrayList<>();
        hosts.add(new HostSimple(2, 4096, 10000)  // 2核CPU，4GB内存，10TB存储
                .setRamProvisioner(new RamProvisionerSimple(4096))  // 内存分配策略
                .setBwProvisioner(new BwProvisionerSimple(10000))   // 带宽分配策略
                .setVmScheduler(new VmSchedulerTimeShared()));      // CPU调度策略

        // 数据中心配置
        return new Datacenter("Datacenter", characteristics,
                new VmAllocationPolicySimple(hosts),  // 虚拟机分配策略
                new ArrayList<>(), 0);
    }
}
