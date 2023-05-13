package kr.co.aike.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController2 {
	
	@GetMapping("/hello2")
	public String hello(Model model, @RequestParam(value="name", required = false) String name) {
		model.addAttribute("greeting", "안녕하세요,"+name);
		return "hello2";
	}

}
