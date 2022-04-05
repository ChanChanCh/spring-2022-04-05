package kr.co.songjava.mvc.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Board {
	
	private int boardSeq;
	private String title;
	private String contents;
	private Date regDate;
	

}
