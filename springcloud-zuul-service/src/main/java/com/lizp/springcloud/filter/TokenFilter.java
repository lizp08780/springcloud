package com.lizp.springcloud.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

//http://127.0.0.1:8015/product/port?token=123456
public class TokenFilter extends ZuulFilter {

	private final Logger LOGGER = LoggerFactory.getLogger(TokenFilter.class);

	// 返回值为过滤器的类型
	@Override
	public String filterType() {
		return "pre"; // pre表示在路由之前执行过滤器，其他可选值还有post、error、route和static
	}

	@Override
	public int filterOrder() {
		return 0; // 过滤器的执行顺序，通过数字指定 ,优先级为0，数字越大，优先级越低
	}

	@Override
	public boolean shouldFilter() {
		return true; // 判断过滤器是否执行，true表示执行，false表示不执行
	}

	// 过滤的具体逻辑
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		LOGGER.info("--->>> TokenFilter {},{}", request.getMethod(), request.getRequestURL().toString());

		String token = request.getParameter("token");// 获取请求的参数

		if (StringUtils.isNotBlank(token)) {
			ctx.setSendZuulResponse(true); // 对请求进行路由
			ctx.setResponseStatusCode(200);
			ctx.set("isSuccess", true);
			return null;
		} else {
			ctx.setSendZuulResponse(false); // 不对其进行路由
			ctx.setResponseStatusCode(400);
			ctx.setResponseBody("token is empty");
			ctx.set("isSuccess", false);
			return null;
		}
	}

}
