package com.zd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zd.bean.User;
import com.zd.biz.UserBiz;
import com.zd.biz.impl.UserBizImpl;
import com.zd.web.model.JsonModel;

public class UserServlet extends BasicServlet{
	private static final long serialVersionUID = 1L;
	private UserBiz ub=new UserBizImpl();
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if("registerUser".equals(op)){
				registerUserOP(request,response);
			}else if("Userlogin".equals(op)){
				UserloginOP(request,response);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}

	private void UserloginOP(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user=(User) this.parseRequest(request, User.class);
		PrintWriter out=response.getWriter();
		User u=ub.loginUser(user);
		if(u==null){
			out.print("<meta charset='utf-8'><script>alert('用户名或密码错误....');window.location.href='login.html'</script>");
			return;
		}else{
			if(u.getVersion()==0){
				out.print("<meta charset='utf-8'><script>alert('改用户已失效....');window.location.href='login.html'</script>");
				return;
			}
			if(u.getStatus()==1){
				HttpSession s1=request.getSession();
				String name=u.getUserId();
				URLEncoder.encode(name, "UTF-8");
				Cookie  mycookie = new Cookie("username",name);
				mycookie.setMaxAge(604800);
				response.addCookie(mycookie);
				s1.setAttribute("User",u);
				request.getRequestDispatcher("manage.html").forward(request, response);
			}else{
				out.print("<meta charset='utf-8'><script>alert('未知错误...');window.location.href='login.html'</script>");
				return;
			}
			
		}
	}

	private void registerUserOP(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user=(User) this.parseRequest(request, User.class);
		PrintWriter out=response.getWriter();
		int result;
		try {
			result = ub.registerUser(user);
			response.sendRedirect("reg_success.html");
		} catch (Exception e) {
			e.printStackTrace();
			out.print("<meta charset='utf-8'><script>alert('注册失败,该账号已被使用');window.location.href='register.html'</script>");
		}
	}
}
