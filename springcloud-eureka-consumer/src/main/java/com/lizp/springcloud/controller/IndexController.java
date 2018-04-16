package com.lizp.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/consumer")
public class IndexController {
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap map) {
		map.put("title", "欢迎");
		return "index";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public List<String> test() {
		System.err.println("begin...");
		return restTemplate.getForObject("http://product-service/product/test", List.class);
	}

}
