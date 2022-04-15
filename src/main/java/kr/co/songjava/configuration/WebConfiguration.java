package kr.co.songjava.configuration;

import java.util.List;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import kr.co.songjava.configuration.servlet.handler.BaseHandlerInterceptor;
import kr.co.songjava.framework.data.web.MySQLPageRequestHandleMethodArgumentResolver;
import kr.co.songjava.mvc.domain.BaseCodeLabelEnum;

// Spring MEssage(다국어 ) 설정 + controllerAdvice 사용법 + 간편한 데이터 검증/ 예외처리
//(순서)
// - 1. WebConfiguration 클래스 생성 및 messageSource 추가
// - 2. 패키지 생성
// - 3. AbstractBaseException 클래스 생성
// - 4. BaseException 클래스생성
// - 5. BaseControllerAdvice 클래스 생성

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		// 스프링에서 제공하는 메세지 소스 클래스를 생성
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.setBasename("classpath:/messages/message"); // set or get name을 통해서 classpath: 의 / messages 폴더에 / message
															// 파일을 읽어들임
		source.setDefaultEncoding("UTF-8"); // DefaultEncoding == ("UTF-8")로 설정
		source.setCacheSeconds(60);
		source.setDefaultLocale(Locale.KOREAN);
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}

	// - 2. WebConfiguration 클래스에 WebMvcConfigurer 인터페이스 구현
	@Bean // BaseHandlerInterceptor를 Bean에 등록
	public BaseHandlerInterceptor baseHandlerInterceptor() {
		return new BaseHandlerInterceptor();
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(BaseCodeLabelEnum.class, new BaseCodeLabelEnumJsonSerializer());
		objectMapper.registerModule(simpleModule);
		return objectMapper;
	}

	@Bean
	public MappingJackson2JsonView mappingJackson2JsonView() {
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.setContentType(MediaType.APPLICATION_JSON_VALUE);
		jsonView.setObjectMapper(objectMapper());
		return jsonView;
	}

	@Override // addInterceptors를 오버라이드하여 레지스트리에 addInterceptor 메소드를 통해 Bean으로 등록한 메소드들을 콜해서
				// 인터셉터를 추가하는 과정
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(baseHandlerInterceptor());
	}


	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		// 페이지 리졸버 등록
		resolvers.add(new MySQLPageRequestHandleMethodArgumentResolver());
	}
}
