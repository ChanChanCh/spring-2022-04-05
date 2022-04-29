package kr.co.songjava.mvc.parameter;

import java.util.List;

import kr.co.songjava.mvc.domain.BoardType;
import lombok.Data;

/**
 *  게시물 검색 파라메터
 * @author root
 */

@Data
public class BoardSearchParameter {

	private String keyword;
	private BoardType[] boardTypes;
	
	public BoardSearchParameter() {

	}

}
