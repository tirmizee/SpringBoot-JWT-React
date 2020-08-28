package com.tirmizee.api.event;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

import com.tirmizee.common.response.ResponseData;
import com.tirmizee.service.MemoryService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping(path = "/event")
public class ServerSendEventController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(ServerSendEventController.class);
	
	private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
	
	@Autowired
	private MemoryService memoryService;
	
	@GetMapping(path = "/timing", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ServerSentEvent<Object>> timing() {
	    return Flux.interval(Duration.ofSeconds(1))
	    		.map(sequence -> ServerSentEvent.<Object> builder()
    		        .id(String.valueOf(sequence))
    		        .event("message")
    		        .data( DATETIME_FORMAT.format(LocalDateTime.now()) )
    		        .build());
	}
	
	@GetMapping(path = "/performance", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ServerSentEvent<Object>> performance() {
	    return Flux.interval(Duration.ofSeconds(1))
	    		.map(sequence -> ServerSentEvent.<Object>builder()
    		        .id(String.valueOf(sequence))
    		        .event("message")
    		        .data(memoryService.getPerformanceData())
    		        .build());
	}
	
//	 @GetMapping("/randomNumbers")
//	    public Flux<ServerSentEvent<Integer>> randomNumbers() {
//	        return Flux.interval(Duration.ofSeconds(1))
//	                .map(seq -> Tuples.of(seq, ThreadLocalRandom.current().nextInt()))
//	                .map(data -> ServerSentEvent.<Integer>builder()
//	                        .event("random")
//	                        .id(Long.toString(data.getT1()))
//	                        .data(data.getT2())
//	                        .build());
//	    }
	
	@ExceptionHandler(value = AsyncRequestTimeoutException.class)  
    public ResponseData<Object> asyncTimeout(AsyncRequestTimeoutException e){  
        return null;
    }

}
