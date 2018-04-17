package com.lizp.springcloud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;

import rx.Observable;
import rx.Subscriber;

@Service
public class ConsumerService {
	@Autowired
	private RestTemplate restTemplate;

	// 同步方式
	@HystrixCommand(fallbackMethod = "error") // error请求失败时回调的方法
	@SuppressWarnings("unchecked")
	public List<String> test() {
		return restTemplate.getForObject("http://product-service/product/test", List.class);
	}

	public List<String> error() {
		List<String> list = new ArrayList<String>();
		list.add("error");
		return list;
	}

	// 异步方式
	@HystrixCommand(fallbackMethod = "error") // error请求失败时回调的方法
	public Future<List<String>> test2() {
		return new AsyncResult<List<String>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<String> invoke() {
				return restTemplate.getForObject("http://product-service/product/test", List.class);
			}
		};
	}

	// @HystrixCommand(observableExecutionMode =
	// ObservableExecutionMode.EAGER)表示使用observe模式来执行
	// @HystrixCommand(observableExecutionMode =
	// ObservableExecutionMode.LAZY)表示使用toObservable模式来执行
	// 响应式函数编程
	@SuppressWarnings({ "deprecation", "unchecked" })
	@HystrixCommand
	public Observable<List<String>> test4() {
		return Observable.create(new Observable.OnSubscribe<List<String>>() {
			@Override
			public void call(Subscriber<? super List<String>> subscriber) {
				if (!subscriber.isUnsubscribed()) {
					List<String> list = restTemplate.getForObject("http://product-service/product/test", List.class);
					subscriber.onNext(list);
					subscriber.onCompleted();
				}
			}
		});
	}
}
