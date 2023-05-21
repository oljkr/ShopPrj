package kr.co.aike.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.aike.domain.Products;
import kr.co.aike.domain.Users;

public interface ProductsService {
	
	public ModelAndView addProducts(Products products, HttpServletRequest request, @RequestParam("thumbnailimg") MultipartFile[] file1, @RequestParam("contentimg") MultipartFile[] file2) throws Exception;

	public ModelAndView productDetail(Products products) throws Exception;

	public ModelAndView preModifyProduct(@ModelAttribute Products products) throws Exception;

	public ModelAndView modifyProduct(Products products, HttpServletRequest request, String[] prdNumList, String[] upperImgList, String[] lowerImgList, MultipartFile[] file1,
			MultipartFile[] file2) throws Exception;

	public ModelAndView preDeleteProduct(Products products) throws Exception;

	public ModelAndView deleteProduct(Products products, HttpServletRequest request) throws Exception;

	public ModelAndView list(Products products) throws Exception;
	
}
