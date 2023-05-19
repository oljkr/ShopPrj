package kr.co.aike.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.aike.domain.Products;
import kr.co.aike.service.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/products")
public class ProductsController {
	private final ProductsService service;
	
	@GetMapping("/add")
	public String preAddProduct() throws Exception {
		return "products/addproduct";
	}
	
	@PostMapping("/add")
	public ModelAndView addProduct(@ModelAttribute Products products, HttpServletRequest request, @RequestParam("thumbnailimg") MultipartFile[] file1, @RequestParam("contentimg") MultipartFile[] file2) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav = service.addProducts(products, request, file1, file2);
		return mav;
	}
}
