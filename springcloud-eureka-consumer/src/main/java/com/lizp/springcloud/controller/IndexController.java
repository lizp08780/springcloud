package com.lizp.springcloud.controller;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lizp.springcloud.hystrixCommand.ListCommand;
import com.lizp.springcloud.service.ConsumerService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

@RestController
@RequestMapping(value = "/consumer")
public class IndexController {
	@Autowired
	private ConsumerService consumerService;
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public List<String> test() {
		System.err.println("begin test...");
		return consumerService.test();
	}

	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	public List<String> test2() throws Exception {
		System.err.println("begin test2...");
		Future<List<String>> future = consumerService.test2();
		return future.get();
	}

	@RequestMapping(value = "/test3", method = RequestMethod.GET)
	public List<String> test3() throws Exception {
		System.err.println("begin test3...");
		ListCommand listCommand = new ListCommand(
				HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")), restTemplate);
		// 异步方式
		Future<List<String>> future = listCommand.queue();
		return future.get();
	}

	@RequestMapping(value = "/test4", method = RequestMethod.GET)
	public List<String> test4() throws Exception {
		System.err.println("begin test4...");
		ListCommand listCommand = new ListCommand(
				HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")), restTemplate);
		// 同步方式
		return listCommand.execute();
	}
}
