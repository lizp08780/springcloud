package com.lizp.springcloud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	@Value("${server.port}")
	private String port;

	public List<String> getTest() {
		List<String> list = new ArrayList<String>();
		list.add("sfsdf");
		list.add("346534");
		list.add("gjghjkgh");
		list.add(port);
		return list;
	}
}
