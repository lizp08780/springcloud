package com.lizp.springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("product-service")
public interface HomeClient {

	@GetMapping("/product/port")
	String port();
}
