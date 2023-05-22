package kr.co.aike.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.aike.domain.Products;
import kr.co.aike.domain.Users;
import kr.co.aike.service.ProductsService;
import kr.co.aike.service.UsersService;
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
	
	@GetMapping("/edit")
	public ModelAndView preEditProduct(@ModelAttribute Products products) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav = service.preModifyProduct(products);
		return mav;
	}
	
	@PostMapping("/edit")
	public ModelAndView editProduct(@ModelAttribute Products products, HttpServletRequest request, @RequestParam("prdNum") String[] prdNumList,  @RequestParam("existingUpperImgNo") String[] upperImgNoList, @RequestParam("existingLowerImgNo") String[] lowerImgNoList, @RequestParam("thumbnailimg") MultipartFile[] file1, @RequestParam("contentimg") MultipartFile[] file2) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav = service.modifyProduct(products, request, prdNumList, upperImgNoList, lowerImgNoList, file1, file2);
		return mav;
	}
	
	@GetMapping("/detail")
	public ModelAndView productDetail(@ModelAttribute Products products) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav = service.productDetail(products);
		return mav;
	}
	
	@GetMapping("/predelete")
	public ModelAndView preDeleteProduct(@ModelAttribute Products products) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav = service.preDeleteProduct(products);
		return mav;
	}
	
	@GetMapping("/delete")
	public ModelAndView deleteProduct(@ModelAttribute Products products, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav = service.deleteProduct(products,request);
		return mav;
	}
	
	@GetMapping("/list")
	public ModelAndView productList(@ModelAttribute Products products) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav = service.list(products);
		return mav;
	}
	
	@ResponseBody
	@PostMapping("/getlist")
	public HashMap<String, List<?>> addList(@RequestParam Map<String, Object> map) throws Exception {
		HashMap<String, List<?>> list = new HashMap();
		list = service.addList(map);
		return list;
	}

}
