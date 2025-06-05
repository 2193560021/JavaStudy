package com.example.simulation;

import org.cloudsimplus.cloudlets.Cloudlet;
import org.cloudsimplus.cloudlets.CloudletSimple;

/**
 * 云任务配置
 */
public class CloudletConfig {
    public static Cloudlet createCloudlet(int cloudletId) {
        return new CloudletSimple(cloudletId, 5000, 1)  // 任务长度5000 MI，1个虚拟机
                .setFileSize(300)  // 文件大小300 MB
                .setOutputSize(300);  // 输出结果大小300 MB
    }
}
