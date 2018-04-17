package com.lizp.springcloud.controller;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lizp.springcloud.service.ConsumerService;

@RestController
@RequestMapping(value = "/consumer")
public class IndexController {
	@Autowired
	private ConsumerService consumerService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public List<String> test() {
		System.err.println("begin...");
		return consumerService.test();
	}

	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	public List<String> test2() throws Exception {
		System.err.println("begin...");
		Future<List<String>> future = consumerService.test2();
		return future.get();
	}
}
