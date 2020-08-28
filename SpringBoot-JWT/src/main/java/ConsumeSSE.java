import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ConsumeSSE {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(ConsumeSSE.class);

	public static void main(String[] args) {
		MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
		MemoryUsage heap = memBean.getHeapMemoryUsage();
	    MemoryUsage nonHeap = memBean.getNonHeapMemoryUsage();
	    System.out.println(ManagementFactory.getThreadMXBean().getThreadCount());
	    System.out.println(ManagementFactory.getThreadMXBean().getDaemonThreadCount());
	    System.out.println(ManagementFactory.getThreadMXBean().getTotalStartedThreadCount());
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");

	    System.out.println(formatter.format(LocalDateTime.now()));	   
	    System.out.println(formatter.format(LocalDateTime.now()));	   
	    System.out.println(formatter.format(LocalDateTime.now()));	   
//
	    
	    com.sun.management.OperatingSystemMXBean bean = (com.sun.management.OperatingSystemMXBean) ManagementFactory
	            .getOperatingSystemMXBean();
	    System.out.println(bean.getFreePhysicalMemorySize());
	    System.out.println(bean.getProcessCpuTime());
	    System.out.println(bean.getTotalPhysicalMemorySize());
	        System.out.println(bean.getProcessCpuLoad());
	        System.out.println(bean.getSystemCpuLoad());
	    
	}

}
