package kr.co.songjava.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.songjava.configuration.GlobalConfig;
import kr.co.songjava.configuration.exception.BaseException;
import kr.co.songjava.configuration.http.BaseResponse;
import kr.co.songjava.configuration.http.BaseResponseCode;
import kr.co.songjava.mvc.parameter.UploadFileParameter;
import kr.co.songjava.mvc.service.UploadFileService;


@RestController

@RequestMapping("/file")

@Api(tags = "파일 API")

public class FileController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private GlobalConfig config;
	
	@Autowired
	private UploadFileService uploadFileService;
	

	/**
	 * 
	 * 업로드 리턴.
	 * @return
	 * 
	 */
								//필수체크 로직
	@PostMapping("/save")      
	@ApiOperation(value = "업로드", notes = "")
	public BaseResponse<Boolean> save(@RequestParam("uploadFile") MultipartFile multipartFile) {
		logger.debug("MultipartFile : {}", multipartFile);
		// 파일첨부에는 무조건 파일을 선택하게끔 필수 체크기능 넣음! <필수체크로직>
		if (multipartFile == null || multipartFile.isEmpty()) {
			throw new BaseException(BaseResponseCode.DATA_IS_NULL);
		}
		//날짜 폴더를 추가
		String currentDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		String uploadFilePath = config.getUploadFilePath() + currentDate + "/";
		logger.debug("uploadFilePath : {}", uploadFilePath);
		
		// 파일저장
		String prefix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1, multipartFile.getOriginalFilename().length());
		String filename = UUID.randomUUID().toString() + "." + prefix;
		logger.info("filename : {}", filename);
		
		File folder = new File(uploadFilePath);
		//폴더가 없다면 생성
		if (!folder.isDirectory()) {
			folder.mkdirs();
		}
		//(파일이 저장될 상위경로) example C:/home/upload
		String pathname = uploadFilePath + filename;
		String resourcePathname = config.getUploadResourcePath() + currentDate + "/" + filename;
		File dest = new File(pathname);
		logger.debug("dest : {}", dest);
		try {
			multipartFile.transferTo(dest);
			//파일업로드 된 후 DB에 저장
			UploadFileParameter parameter = new UploadFileParameter();
			//컨텐츠 종류
			parameter.setContentType(multipartFile.getContentType());
			//원본파일명
			parameter.setOriginalFilename(multipartFile.getOriginalFilename());
			//저장파일명
			parameter.setFilename(filename);
			//실제파일 저장경로
			parameter.setPathname(pathname);
			//파일크기
			parameter.setSize((int) multipartFile.getSize());
			//static resource 접근 경로
			parameter.setResourcePathname(resourcePathname);
			uploadFileService.save(parameter);		
		} catch (IllegalStateException | IOException e) {
			logger.error("e", e);
		}
		
	return new BaseResponse<Boolean>(true);
	}

}