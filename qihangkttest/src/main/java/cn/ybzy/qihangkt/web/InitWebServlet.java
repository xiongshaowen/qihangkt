package cn.ybzy.qihangkt.web;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.ybzy.qihangkt.service.ResourceService;

public class InitWebServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// 做两个初始化
	// 1.spring的ICO容器的引用变量，拿到它
	// 2.权限初化
	private static ApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override // 点source---Override/implements...--init()
	public void init() throws ServletException {
		// ICO容器获取
		ServletContext context = getServletContext();// web项目中最大的域对象
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
		try {
			// 权限控制初始化
			// packageName实施权限控制的包全名
			String packageName = "cn.ybzy.qihangkt.controller";
			String packageNamePath = packageName.replace(".", "/"); // --->cn/ybzy/qihangkt/controller
			// 进一步的获取到控制层对应在服务器磁盘上的绝对路径
			String packageNameRealPath = this.getClass().getClassLoader().getResource(packageNamePath).getPath();
			System.out.println(packageNameRealPath); // -->/C:/Users/Administrator/Desktop/apache-tomcat-8.5.55/webapps/qihangkttest/WEB-INF/classes/cn/ybzy/qihangkt/controller/
			File file = new File(packageNameRealPath); // 产生控制层的文件夹
			String[] classFileNames = file.list(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) { // 过滤后缀为.class的文件，这是系统自定的方法我们在些重写
					if (name.endsWith(".class")) {
						return true;
					}
					return false;
				}
			});
			// 构造出这个class文件的包全名
			List<String> pathes = new ArrayList<>();   //权限控制路径集合
			for (String classFileName : classFileNames) {
				// .class这个后缀给删除掉
				classFileName = classFileName.substring(0, classFileName.indexOf(".class"));
				// 拿到纯粹的类的包全名如：cn.ybzy.qihangkt.controller.AdminController
				String classAllpackageName = packageName + "." + classFileName;
				// 通过反射，获取到对应类的对象
				Class<?> clazz = Class.forName(classAllpackageName);
				// 拿到这些controller的对象，获取到在他们身上的注解，如果没有@AuthClass，则表明不执行此类，放掉
				//剩下的控制类，都是有@AuthClass这个注解的类，这些类都要进行权限控制
				if (!clazz.isAnnotationPresent(AuthClass.class)) continue;
				Method[] methods = clazz.getDeclaredMethods(); //包括private方法
				for(Method method:methods) {
					if(!method.isAnnotationPresent(AuthMethod.class)) continue;
					//都是有@AuthMethod的方法，拿到要保存到权限表里t_resource-->path字段中
					RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
					//System.out.println(requestMapping.value()[0]);  //-->/admin/admin.html ,/admin/user_manager.html...
					pathes.add(requestMapping.value()[0]);
				}
			}
			//List<String> pathes: 包含了controller包里，所有被@AuthClass和@AuthMethod共同作用的方法上面的@RequestMapping的value值，都在里面
			//pathes放到表t_resource，获取到ResourceService,通过ResourceService对象调用ResourceDao里sql语句执行添加到表的操作
		    ResourceService resourceService = (ResourceService) applicationContext.getBean("resourceService");
		    resourceService.initPathes(pathes);
		    context.setAttribute("allPermissionPathes", pathes);  //全局域空间中要保存这些路径（权限）
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
