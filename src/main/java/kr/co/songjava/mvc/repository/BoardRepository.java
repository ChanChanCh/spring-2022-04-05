package kr.co.songjava.mvc.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import kr.co.songjava.mvc.domain.Board;
import kr.co.songjava.mvc.service.BoardService;

/*
 * 게시판 Repository
 * @author Root
 */

@Repository
public interface BoardRepository {
	
	//리스트
	List<Board> getList();
	
	//  단건?
	Board get(int boardSeq);
	
	// 등록
	void save(Board board);
	
	// 업데이트
	void update(Board board);
	
	// 삭제
	void delete(int boardSeq);
		
	// 후에 xml과 연결이되 실행이됨
}


