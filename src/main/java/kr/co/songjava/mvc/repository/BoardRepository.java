package kr.co.songjava.mvc.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.co.songjava.framework.data.domain.PageRequestParameter;
import kr.co.songjava.mvc.domain.Board;
import kr.co.songjava.mvc.parameter.BoardParameter;
import kr.co.songjava.mvc.parameter.BoardSearchParameter;

/*
 * 게시판 Repository
 * @author Root
 */

@Repository
public interface BoardRepository {
	
	//리스트
	List<Board> getList(PageRequestParameter<BoardSearchParameter> pageRequestParameter);
	
	//  단건?
	Board get(int boardSeq);
	
	// 등록
	void save(BoardParameter board);
	
	void saveList(Map<String, Object> paramMap);
	
	// 업데이트
	void update(BoardParameter board);
	
	// 삭제
	void delete(int boardSeq);
		
	// 후에 xml과 연결이되 실행이됨
}


