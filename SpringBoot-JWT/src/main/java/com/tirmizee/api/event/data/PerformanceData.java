package com.tirmizee.api.event.data;

import lombok.Data;

@Data
public class PerformanceData {

	private Long maxMemory;
	private Long usedMemory;
	private Long maxMemoryNonHeap;
	private Long usedMemoryNonHeap;
	private Long cpuTime;
	private Integer threadCount;
	private Integer daemonThread;
	private Long startedThread;
	private Integer peakThread;
	
}
