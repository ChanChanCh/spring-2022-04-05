package kr.co.songjava.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

// Spring MEssage(다국어 ) 설정 + controllerAdvice 사용법 + 간편한 데이터 검증/ 예외처리
//(순서)
// - 1. WebConfiguration 클래스 생성 및 messageSource 추가
// - 2. 패키지 생성
// - 3. AbstractBaseException 클래스 생성
// - 4. BaseException 클래스생성
// - 5. BaseControllerAdvice 클래스 생성




@Configuration
public class WebConfiguration {
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		//스프링에서 제공하는 메세지 소스 클래스를 생성
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.setBasename("classpath:/messages/message"); // set or get name을 통해서  classpath: 의  / messages 폴더에 / message 파일을 읽어들임
		source.setDefaultEncoding("UTF-8");    // DefaultEncoding == ("UTF-8")로 설정
		source.setCacheSeconds(60);
		source.setDefaultLocale(Locale.KOREAN);
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}
	

}
