package kr.co.aike.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Products {
	private Long prdNo;
	private String sort1;
	private String sort2;
	private String name;
	private String color;
	private String size;
	private String shortDes;
	private String fullDes;
	private int stock;
	private int price;
	private int orderCnt;

}
