package kr.co.songjava.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.songjava.mvc.domain.Board;
import kr.co.songjava.mvc.parameter.BoardParameter;
import kr.co.songjava.mvc.service.BoardService;


/*
 * 게시판 컨트롤러
 * @author Root
 */
@RestController
@RequestMapping("/board")  // 1-1. 이렇게 RequestMapping을 달아주면 /board를 쳤을때 
@Api(tags = "게시판API")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	/*
	 * 목록 리턴
	 * @return
	 */
	@GetMapping			// 1-2. 이부분이 실행되게 된다
	@ApiOperation(value ="목록 조회",  notes = "게시물 목록 정보를 조회할 수 있습니다.")
	public List<Board> getList() {
		return boardService.getList();
	}

	/*
	 * 상세 정보 리턴
	 * @param boardSeq
	 * @return
	 */
	@GetMapping("/{boardSeq}") // 2.1 /board/0 을 입력하면 boardSeq 0번째 가 실행됨
	@ApiOperation(value ="상세 조회",  notes = "게시물 번호에 해당하는 상세 정보를 조회할 수 있습니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1")
	})
	public Board get(@PathVariable int boardSeq) {
		return boardService.get(boardSeq);
	}

	/*
	 * 등록 / 수정 처리.
	 * @param board
	 */
	@PutMapping
	@ApiOperation(value ="등록/ 수정 처리",  notes = "신규 게시물 저장 및 기존 게시물 업데이트가 가능합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1"),
		@ApiImplicitParam(name = "title", value = "제목", example = "spring"),
		@ApiImplicitParam(name = "content", value = "내용", example = "spring 강좌")
	})
	public int save(BoardParameter parameter) {
		boardService.save(parameter);
		return parameter.getBoardSeq();
	}
	
	

	
	/*
	 * 삭제 처리
	 * @param boardSeq
	 */
	@DeleteMapping("/delete/{boardSeq}")
	@ApiOperation(value ="삭제 처리",  notes = "게시물 번호에 해당하는 정보를 삭제합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1"),
	})
	public boolean delete(@PathVariable int boardSeq) {
		Board board = boardService.get(boardSeq);
		if (board == null) {
			return false;
		}
		boardService.delete(boardSeq);
		return true;
	}
	
}

