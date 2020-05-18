package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 根路径以及其他请求处理
 * @author Administrator
 *
 */
@Controller
public class IndexController {

	
	
	/**
	 * 登录请求
	 * @return
	 */
	@RequestMapping("/login")
	public String login(){
		return "/login";
	}

}
