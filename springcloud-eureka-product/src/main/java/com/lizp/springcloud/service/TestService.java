package com.lizp.springcloud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TestService {
	public List<String> getTest() {
		List<String> list = new ArrayList<String>();
		list.add("sfsdf");
		list.add("346534");
		list.add("gjghjkgh");
		return list;
	}
}
