package kr.co.aike.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Order {
	private Long orderNo;
	private Long prdNo;
	private int quantity;
	private String paymentMethod;
	private String status;
	private Long shipInfoNo;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime payTime;

}
