package com.lizp.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lizp.springcloud.service.TestService;

@RefreshScope
@RestController
@RequestMapping(value = "/product")
public class IndexController {
	@Autowired
	private TestService testService;
	@Value("${sang}")
	private String sang;
//	@Autowired
//	private Environment env;

	@RequestMapping("/sang")
	public String sang() {
		return this.sang;
	}

//	@RequestMapping("/sang2")
//	public Environment sang2() {
//		return env;
//	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public List<String> test() {
		System.err.println("end...");
		return testService.getTest();
	}

}
