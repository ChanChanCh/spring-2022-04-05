package kr.co.songjava.mvc.repository;

import org.springframework.stereotype.Repository;

import kr.co.songjava.mvc.parameter.UploadFileParameter;

/*
 * 업로드 파일 Repository
 * @author Root
 */

@Repository
public interface UploadFileRepository {
	void save(UploadFileParameter parameter);
}
