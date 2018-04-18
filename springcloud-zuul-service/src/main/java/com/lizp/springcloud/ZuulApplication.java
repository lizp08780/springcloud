package com.lizp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.lizp.springcloud.filter.PasswordFilter;
import com.lizp.springcloud.filter.TokenFilter;

@EnableDiscoveryClient
@EnableZuulProxy // 开启 Zuul 服务网关
@SpringBootApplication
public class ZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}

	@Bean
	public TokenFilter tokenFilter() {
		return new TokenFilter();
	}

	@Bean
	public PasswordFilter PasswordFilter() {
		return new PasswordFilter();
	}

}
