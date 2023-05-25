package kr.co.aike.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartItem {
	private Long prdNo;
    private String prdName;
    private String prdImgFileName;
    private String color;
    private String size;
    private int quantity;
    private int price;
}
