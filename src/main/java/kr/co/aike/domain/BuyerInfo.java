package kr.co.aike.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BuyerInfo {
	private Long buyerInfoNo;
	private String name;
	private String email;
	private String phone;
	private String buyerNo;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime buyTime;
}
