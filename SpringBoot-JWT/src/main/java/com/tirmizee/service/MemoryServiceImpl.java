package com.tirmizee.service;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.lang.management.ThreadMXBean;

import org.springframework.stereotype.Service;

import com.tirmizee.api.event.data.PerformanceData;

@Service
public class MemoryServiceImpl implements MemoryService {

	@Override
	public PerformanceData getPerformanceData() {
		MemoryUsage memory = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
		MemoryUsage memoryNonHeap = ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage();
		ThreadMXBean thread = ManagementFactory.getThreadMXBean();
		PerformanceData performance = new PerformanceData();
		performance.setMaxMemory(memory.getMax());
		performance.setUsedMemory(memory.getUsed());
		performance.setMaxMemoryNonHeap(memoryNonHeap.getMax());
		performance.setUsedMemoryNonHeap(memoryNonHeap.getUsed());
		performance.setThreadCount(thread.getThreadCount());
		performance.setDaemonThread(thread.getDaemonThreadCount());
		performance.setStartedThread(thread.getTotalStartedThreadCount());
		performance.setPeakThread(thread.getPeakThreadCount());
		return performance;
	}

}
