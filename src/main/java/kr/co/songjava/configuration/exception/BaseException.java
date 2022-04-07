package kr.co.songjava.configuration.exception;

import kr.co.songjava.configuration.http.BaseResponseCode;

//방금전에 만든 추상클래스를 상속받아서 구현								
// 간단하게 기본적인 예외처리 클래스 완성

public class BaseException extends AbstractBaseException {

	private static final long serialVersionUID = 8342235231880246631L;

	public BaseException() {
	}

	public BaseException(BaseResponseCode responseCode) {
		this.responseCode = responseCode;
	}

	public BaseException(BaseResponseCode responseCode, String[] args) {
		this.responseCode = responseCode;
		this.args = args;
	}
}