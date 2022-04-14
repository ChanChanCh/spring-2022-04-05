package kr.co.songjava.mvc.parameter;

import lombok.Data;

/**
 *  게시물 검색 파라메터
 * @author root
 */

@Data
public class BoardSearchParameter {

	private String keyword;
	
	public BoardSearchParameter() {

	}

}
