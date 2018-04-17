package com.lizp.springcloud.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(value = "/consumer")
public class IndexController {
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@HystrixCommand(fallbackMethod = "error") // error请求失败时回调的方法
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public List<String> test() {
		System.err.println("begin...");
		return restTemplate.getForObject("http://product-service/product/test", List.class);
	}

	public List<String> error() {
		List<String> list = new ArrayList<String>();
		list.add("error");
		return list;
	}
}
