package kr.co.songjava.configuration.servlet.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// - 1. BaseHandlerInterceptor 추가

public class BaseHandlerInterceptor extends HandlerInterceptorAdapter {
								// 이 클래스에선 스프링에서 제공하는 HandlerInterceptorAdapter 라는 클래스를 상속받아 구현한다
	Logger logger = LoggerFactory.getLogger(getClass());

	@Override		//preHandle과 postHandle 을 오버라이드하여 log를 찍어준다
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("preHandle requestURI : {}", request.getRequestURI());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("postHandle requestURI : {}", request.getRequestURI());
	}
}