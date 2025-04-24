package com.mc.esstatistic.utils;

import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.OSFileStore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SystemInfoUtils {

    private final SystemInfo systemInfo = new SystemInfo();
    private final HardwareAbstractionLayer hardware = systemInfo.getHardware();

    // 获取所有系统信息
    public Map<String, Object> getSystemInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("disk", getDiskInfo());
        info.put("cpu", getCpuUsage());
        info.put("memory", getMemoryInfo());
        return info;
    }

    // 获取磁盘信息（修复List类型问题）
    private Map<String, Object> getDiskInfo() {
        List<Map<String, Object>> disks = new ArrayList<>();
        List<OSFileStore> fileStores = systemInfo.getOperatingSystem().getFileSystem().getFileStores(); // 改为List
        for (OSFileStore fs : fileStores) {
            Map<String, Object> disk = new HashMap<>();
            disk.put("name", fs.getName());
            disk.put("total", bytesToGb(fs.getTotalSpace()));
            disk.put("free", bytesToGb(fs.getFreeSpace()));
            disk.put("used", bytesToGb(fs.getTotalSpace() - fs.getFreeSpace()));
            disk.put("usageRate", (double) (fs.getTotalSpace() - fs.getFreeSpace()) / fs.getTotalSpace());
            disks.add(disk);
        }
        Map<String, Object> diskInfo = new HashMap<>();
        diskInfo.put("disks", disks);
        return diskInfo;
    }

    // 获取CPU使用率（修复新API调用）
    private Map<String, Object> getCpuUsage() {
        CentralProcessor processor = hardware.getProcessor();
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        try {
            Thread.sleep(1000); // 等待1秒采样间隔
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        long[] currentTicks = processor.getSystemCpuLoadTicks();
        double load = processor.getSystemCpuLoadBetweenTicks(prevTicks);
        Map<String, Object> cpuInfo = new HashMap<>();
        cpuInfo.put("usage", load);
        return cpuInfo;
    }

    // 获取内存信息（保持不变）
    private Map<String, Object> getMemoryInfo() {
        GlobalMemory memory = hardware.getMemory();
        long total = memory.getTotal();
        long available = memory.getAvailable();
        long used = total - available;
        Map<String, Object> memoryInfo = new HashMap<>();
        memoryInfo.put("total", bytesToGb(total));
        memoryInfo.put("used", bytesToGb(used));
        memoryInfo.put("available", bytesToGb(available));
        memoryInfo.put("usageRate", (double) used / total);
        return memoryInfo;
    }

    // 字节转GB（添加单位转换）
    private double bytesToGb(long bytes) {
        return bytes / (1024.0 * 1024.0 * 1024.0);
    }
}