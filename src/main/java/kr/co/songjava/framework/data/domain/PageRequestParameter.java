package kr.co.songjava.framework.data.domain;

import lombok.Data;

/**
 * 페이지 요청정보와 파라메터 정보.(2가지 정보를 담아서 Mybatis에서 사용할 수 있음)
 * @author root
 * @param <T>
 */
@Data
public class PageRequestParameter<T> {

	private MySQLPageRequest pageRequest;
	private T parameter;

	public PageRequestParameter(MySQLPageRequest pageRequest, T parameter) {
		this.pageRequest = pageRequest;
		this.parameter = parameter;
	}

}