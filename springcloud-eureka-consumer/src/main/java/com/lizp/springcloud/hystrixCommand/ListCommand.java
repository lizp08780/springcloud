package com.lizp.springcloud.hystrixCommand;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommand;

//自定义HystrixCommand
public class ListCommand extends HystrixCommand<List<String>> {
	private RestTemplate restTemplate;

	// 服务调用失败时回调
	@Override
	protected List<String> getFallback() {
		List<String> list = new ArrayList<String>();
		list.add("error1");
		list.add("error2");
		return list;
	}

	public ListCommand(Setter setter, RestTemplate restTemplate) {
		super(setter);
		this.restTemplate = restTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<String> run() throws Exception {
		return restTemplate.getForObject("http://product-service/product/test", List.class);
	}
}
