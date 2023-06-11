package kr.co.aike.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.aike.service.OrderService;
import kr.co.aike.service.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {
	private final ProductsService prdservice;
	
	@GetMapping("/home")
	public String hello(Model model, @RequestParam(value="name", required = false) String name) {
		model.addAttribute("greeting", "안녕하세요,"+name);
		return "index";
	}
	
	@GetMapping("/navlink1")
	public String navLink1(Model model, @RequestParam(value="name", required = false) String name) {
		model.addAttribute("greeting", "안녕하세요,"+name);
		return "navlink1";
	}
	
	@GetMapping("/navlink2")
	public String navLink2(Model model, @RequestParam(value="name", required = false) String name) {
		model.addAttribute("greeting", "안녕하세요,"+name);
		return "navlink2";
	}
	
	@GetMapping("/navlink3")
	public String navLink3(Model model, @RequestParam(value="name", required = false) String name) {
		model.addAttribute("greeting", "안녕하세요,"+name);
		return "navlink3";
	}
	
	@ResponseBody
	@PostMapping("/searchproduct")
	public HashMap<String, List<?>> searchProduct(@RequestParam Map<String, Object> map) throws Exception {
		HashMap<String, List<?>> list = new HashMap();
		list = prdservice.searchProduct(map);
		return list;
	}

}
