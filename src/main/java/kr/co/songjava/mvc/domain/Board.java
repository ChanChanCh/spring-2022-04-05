package kr.co.songjava.mvc.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class Board {

	private int boardSeq;
	private BoardType boardType;
	private String title;
	private String contents;
	private Date regDate;
	private boolean delYn;
	
}
