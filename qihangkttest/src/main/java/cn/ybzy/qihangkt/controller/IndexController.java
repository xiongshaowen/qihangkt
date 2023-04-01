package cn.ybzy.qihangkt.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//前端显示首页，输入 :   项目名/，项止名/inex.html,项目名/--都可访问到首面
@Controller
public class IndexController {
	@GetMapping(value= {"/index.html","/","index"})
   public String index() {
	   return "index";
   }
	
	@RequestMapping(value= {"/getIndex.html"},method=RequestMethod.GET)
	public String getIndex(Model model,HttpServletRequest request) {
		model.addAttribute("name","get请求过来的值--"+request.getParameter("name"));
		return "index1";
	 }
	@RequestMapping(value="/postIndex.html",method=RequestMethod.POST)
	public String postIndex(Model model,HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		model.addAttribute("name","post请求过来的值--"+request.getParameter("name"));
		System.out.println(request.getParameter("name"));
		return "index1";
	}
}
