package kr.co.aike.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Users {
	private Long userNo;
	private String userId;
	private String userName;
	private String userEmail;
	private String userPw;
	private String zipcode;
	private String address1;
	private String address2;
	private String roles;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime joinDate;

}
