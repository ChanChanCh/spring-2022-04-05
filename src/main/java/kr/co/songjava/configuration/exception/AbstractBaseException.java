package kr.co.songjava.configuration.exception;

import kr.co.songjava.configuration.http.BaseResponseCode;


//추상클래스를 만드는 이유 : 프로젝트에서 커스텀으로  api를 추가할경우 공통 에러포멧이나 코드가 있다 
//이것들을 미리 추상클래스를 만들어 상황에 맞게 해당 클래스를 상속하여 구현하면 간단하게 예외처리 클래스를 만들수 있기때문


public abstract class AbstractBaseException extends RuntimeException  {
		
	  private static final long serialVersionUID = 8342235231880246631L;
	  
	  protected BaseResponseCode responseCode;
	  protected Object[] args;
	  
	  public AbstractBaseException() {
	  }
	  
	  public AbstractBaseException(BaseResponseCode responseCode) {
	  	this.responseCode = responseCode;
	  }
	  
	  public BaseResponseCode getResponseCode() {
	  	return responseCode;
	  }
	  
	  public Object[] getArgs() {
	  	return args;
	  }
}
