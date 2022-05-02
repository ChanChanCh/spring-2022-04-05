package kr.co.songjava.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.songjava.configuration.exception.BaseException;
import kr.co.songjava.configuration.http.BaseResponse;
import kr.co.songjava.configuration.http.BaseResponseCode;
import kr.co.songjava.framework.data.domain.MySQLPageRequest;
import kr.co.songjava.framework.data.domain.PageRequestParameter;
import kr.co.songjava.framework.data.web.bind.annotation.RequestConfig;
import kr.co.songjava.mvc.domain.Board;
import kr.co.songjava.mvc.parameter.BoardParameter;
import kr.co.songjava.mvc.parameter.BoardSearchParameter;
import kr.co.songjava.mvc.service.BoardService;

/**
 * 게시판 컨트롤러
 * 
 * @author root
 */
@Controller
@RequestMapping("/board")
@Api(tags = "게시판 API")
public class BoardController { // 1-1. 이렇게 RequestMapping을 달아주면 /board를 쳤을때

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BoardService boardService;

	/**
	 * 목록 리턴.
	 * @param parameter
	 * @param pageable
	 * @return
	 */
	@GetMapping ("/list")
	public void list(BoardSearchParameter parameter, MySQLPageRequest pageRequest, Model model) {
		logger.info("pageRequest : {}", pageRequest);
		PageRequestParameter<BoardSearchParameter> pageRequestParameter = new PageRequestParameter<BoardSearchParameter>(pageRequest, parameter);
		List<Board> boardList = boardService.getList(pageRequestParameter);
		model.addAttribute("boardList", boardList);
	}

	// 프론트엔드 개발자에게 데이터를 보낼때 상위에 하나의 BaseResponse라는 클래스를 만들어 씌우고 그안에 제네릭으로 데이터를 받는다
	/**
	 * 상세 페이지.
	 * 
	 * @param boardSeq
	 * @param model
	 * @return
	 */
	@GetMapping("/{boardSeq}") // 2.1 /board/0 을 입력하면 boardSeq 0번째 가 실행됨
	public String detail(@PathVariable int boardSeq, Model model) {
		Board board = boardService.get(boardSeq);
		if (board == null) {
			throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] { "게시물" });
		}
		model.addAttribute("board",board);
		return "/board/detail";
	}

	/**
	 * 등록 화면.
	 * 
	 * @param parameter
	 * @param model
	 */
	
	@GetMapping("/form")
	@RequestConfig(loginCheck = false)
	public void form(BoardParameter parameter, Model model) {
		model.addAttribute("parameter", parameter);
	}
	
	
	/**
	 * 수정 화면.
	 * 
	 * @param parameter
	 * @param model
	 */
	
	@GetMapping("/edit/{boardSeq}")
	@RequestConfig(loginCheck = false)
	public  String edit(@PathVariable(required = true) int boardSeq, BoardParameter parameter, Model model) {
			Board board = boardService.get(parameter.getBoardSeq());
			// null 처리
			if (board == null) {
				throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] { "게시물" });
			}
			model.addAttribute("board", board);
			model.addAttribute("parameter", parameter);
			return "/board/form";
	}
	
	
	
	/**
	 * 등록/수정 처리.
	 * 
	 * @param parameter
	 */
	@PostMapping("/save")
	@RequestConfig(loginCheck = false)
	@ResponseBody
	@ApiOperation(value = "등록 / 수정 처리", notes = "신규 게시물 저장 및 기존 게시물 업데이트가 가능합니다.")
	@ApiImplicitParams({ 
			@ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1"),
			@ApiImplicitParam(name = "title", value = "제목", example = "spring"),
			@ApiImplicitParam(name = "contents", value = "내용", example = "spring 강좌"), })
	public BaseResponse<Integer> save(BoardParameter parameter) {
		// 제목 필수 체크
		if (StringUtils.isEmpty(parameter.getTitle())) {
			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] { "title", "제목" });
		}
		// 내용 필수 체크
		if (StringUtils.isEmpty(parameter.getTitle())) {
			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] { "contents", "제목" });
		}
		boardService.save(parameter);
		return new BaseResponse<Integer>(parameter.getBoardSeq());
	}

	/**
	 * 대용량 등록 처리.
	 */
	@ApiOperation(value = "대용량 등록처리1", notes = "대용량 등록처리1")
	@PutMapping("/saveList1")
	public BaseResponse<Boolean> saveList1() {
		int count = 0;
		// 테스트를 위한 랜덤 1000건의 데이터를 생성
		List<BoardParameter> list = new ArrayList<BoardParameter>();
		while (true) {
			count++;
			String title = RandomStringUtils.randomAlphabetic(10);
			String contents = RandomStringUtils.randomAlphabetic(10);
			list.add(new BoardParameter(title, contents));
			if (count >= 10000) {
				break;
			}
		}
		long start = System.currentTimeMillis();
		boardService.saveList1(list);
		long end = System.currentTimeMillis();
		logger.info("실행 시간 : {}", (end - start) / 1000.0);
		return new BaseResponse<Boolean>(true);
	}

	/**
	 * 대용량 등록 처리.
	 */
	@PutMapping("/saveList2")
	@ApiOperation(value = "대용량 등록처리2", notes = "대용량 등록처리2")
	public BaseResponse<Boolean> saveList2() {
		int count = 0;
		// 테스트를 위한 랜덤 1000건의 데이터를 생성
		List<BoardParameter> list = new ArrayList<BoardParameter>();
		while (true) {
			count++;
			String title = RandomStringUtils.randomAlphabetic(10);
			String contents = RandomStringUtils.randomAlphabetic(10);
			list.add(new BoardParameter(title, contents));
			if (count >= 10000) {
				break;
			}
		}
		long start = System.currentTimeMillis();
		boardService.saveList2(list);
		long end = System.currentTimeMillis();
		logger.info("실행 시간 : {}", (end - start) / 1000.0);
		return new BaseResponse<Boolean>(true);
	}

	/**
	 * 삭제 처리.
	 * 
	 * @param boardSeq
	 */
	@DeleteMapping("/{boardSeq}")
	@ApiOperation(value = "삭제 처리", notes = "게시물 번호에 해당하는 정보를 삭제합니다.")
	@ApiImplicitParams({ @ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1"), })
	public BaseResponse<Boolean> delete(@PathVariable int boardSeq) {
		Board board = boardService.get(boardSeq);
		if (board == null) {
			return new BaseResponse<Boolean>(false);
		}
		boardService.delete(boardSeq);
		return new BaseResponse<Boolean>(true);
	}

}
