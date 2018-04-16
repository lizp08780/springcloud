package com.lizp.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lizp.springcloud.service.TestService;

@RestController
@RequestMapping(value = "/product")
public class IndexController {
	@Autowired
	private TestService testService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap map) {
		map.put("title", "欢迎");
		return "index";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public List<String> test() {
		System.err.println("end...");
		return testService.getTest();
	}

}
