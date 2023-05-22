package kr.co.aike.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PrdImg {
	private Long prdImgNo;
	private Long prdNo;
	private String fileName;
	private String location;
	
}
