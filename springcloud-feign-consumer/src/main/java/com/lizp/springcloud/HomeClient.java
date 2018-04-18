package com.lizp.springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("product-service") // 指定服务名进而绑定服务
public interface HomeClient {

	@GetMapping("/product/port")
	String port();

	// 在SpringMVC中，@RequestParam和@RequestHeader注解，如果我们不指定value，则默认采用参数的名字作为其value，但是在Feign中，这个value必须明确指定，否则会报错。
	@RequestMapping(value = "/product/hello", method = RequestMethod.GET)
	String hello(@RequestParam("name") String name);
}
