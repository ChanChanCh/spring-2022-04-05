package kr.co.songjava.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.songjava.mvc.domain.UploadFile;
import kr.co.songjava.mvc.parameter.UploadFileParameter;
import kr.co.songjava.mvc.repository.UploadFileRepository;

@Service
public class UploadFileService {
	
	@Autowired
	private UploadFileRepository repository;
	
	/*
	 * 등록 처리
	 * @param parameter
	 */
	
	public void save(UploadFileParameter parameter) {
		repository.save(parameter);
	}

	public UploadFile get(int uploadFileSeq) {
		return repository.get(uploadFileSeq);
		
		
	}
	
}
