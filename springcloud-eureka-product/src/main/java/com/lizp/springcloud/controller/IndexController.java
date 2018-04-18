package com.lizp.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lizp.springcloud.service.TestService;

@RefreshScope // 使用该注解的类，会在接到SpringCloud配置中心配置刷新的时候，自动将新的配置更新到该类对应的字段中
@RestController
@RequestMapping(value = "/product")
public class IndexController {
	@Autowired
	private TestService testService;
	@Value("${server.port}")
	private String port;
	@Value("${eureka.client.service-url.defaultZone}")
	private String defaultZone;
	@Autowired
	private Environment env;

	@RequestMapping("/port")
	public String port() {
		return this.port + ":" + this.defaultZone;
	}

	@RequestMapping("/env")
	public String sang2() {
		return env.getProperty("server.port");
	}

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
