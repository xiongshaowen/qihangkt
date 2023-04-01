package cn.ybzy.qihangkt.web;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.ybzy.qihangkt.model.User;

//定义权限拦截器
public class AuthIncepertor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String path="";
		if(handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			RequestMapping requestMapping=method.getAnnotation(RequestMapping.class);  //拿到该方法上指定的注解
		    path = requestMapping.value()[0];//
		}else {
			throw new RuntimeException("输入的url地址不存在！");
		}
		//拿到所有权限路径标记
		List<String> allPermissionPathes = (List<String>) request.getServletContext().getAttribute("allPermissionPathes");
		//拿到目前登录的用户，他所拥有的权限对应的权限标记
		List<String > loginUserAllPath = (List<String>) session.getAttribute("loginUserAllPath");
		//拿到目前登录成功的用户对象
		User loginUser = (User) session.getAttribute("loginUser");
		if(loginUser == null) {
			response.sendRedirect(request.getContextPath()+"/login.html");
		}else {
			boolean isAdmin =(boolean) session.getAttribute("isAdmin");
			if(!isAdmin && allPermissionPathes.contains(path)) {
				if(!loginUserAllPath.contains(path)) {
					throw new RuntimeException("你没有该问权限！");
				}
			}
		}
		return super.preHandle(request, response, handler); //springmvc放行
	}
         
}
