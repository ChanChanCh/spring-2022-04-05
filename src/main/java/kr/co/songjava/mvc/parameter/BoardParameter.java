package kr.co.songjava.mvc.parameter;

import kr.co.songjava.mvc.domain.BoardType;
import lombok.Data;

@Data
public class BoardParameter {

	private int boardSeq;
	private BoardType boardType;
	private String title;
	private String contents;
	
	public BoardParameter() {

	}
//	파라미터 첫번째는 기본생성자 2번째는 테스트용 생성자 
	public BoardParameter(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}
	
}
