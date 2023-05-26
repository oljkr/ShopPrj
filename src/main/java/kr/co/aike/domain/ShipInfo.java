package kr.co.aike.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShipInfo {
	private Long shipInfoNo;
	private String zipcode;
	private String address1;
	private String address2;
	private String name;
	private String phone;

}
