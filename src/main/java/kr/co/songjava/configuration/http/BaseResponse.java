package kr.co.songjava.configuration.http;

import lombok.Data;

/**
 * 공통으로 사용할 응답 클래스.
 * @author 송자바
 * @param <T>
 */
@Data
public class BaseResponse<T> {

	private BaseResponseCode code;
	private String message;
	private T data;
	
		//하나의 생성자를 만들어서 지정해줌  
	public BaseResponse(T data) {         // 성공일때 사용
		this.code = BaseResponseCode.SUCCESS;
		this.data = data;
	}
	
				//예외처리일경우 해당 생성자를 사용함
	public BaseResponse(BaseResponseCode Code, String message) {
		this.code = Code;
		this.message = message;
	}
	
}
