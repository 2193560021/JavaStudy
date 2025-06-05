package com.example.simulation;

import org.cloudsimplus.schedulers.cloudlet.CloudletSchedulerTimeShared;
import org.cloudsimplus.vms.Vm;
import org.cloudsimplus.vms.VmSimple;

/**
 * 虚拟机配置
 */
public class VmConfig {

    public static Vm createVm(int vmId) {
        return new VmSimple(vmId, 1000, 1)  // 1GHz CPU，1GB内存
                .setCloudletScheduler(new CloudletSchedulerTimeShared());  // 任务调度策略
    }

}
