package com.lizp.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lizp.springcloud.HomeClient;

@RestController
public class ConsumerController {

	@Autowired
	private HomeClient homeClient;

	@GetMapping(value = "/hello")
	public String hello() {
		return homeClient.port();
	}
}
