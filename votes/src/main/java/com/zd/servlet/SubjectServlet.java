package com.zd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zd.bean.Item;
import com.zd.bean.Subject;
import com.zd.bean.User;
import com.zd.biz.SubjectBiz;
import com.zd.biz.impl.SubjectBizImpl;

public class SubjectServlet extends BasicServlet{
	private static final long serialVersionUID = 1L;
	private SubjectBiz sb=new SubjectBizImpl();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if("selectSubject".equals(op)){
				selectSubjectOP(request,response);
			}else if("doSubject".equals(op)){
				doSubjectOP(request,response);
			}else if("VoteOne".equals(op)){
				VoteOneOP(request,response);
			}else if("addSubject".equals(op)){
				addSubjectOP(request,response);
			}else if("maintenance".equals(op)){
				maintenanceOP(request,response);
			}else if("getSubject".equals(op)){
				getSubjectOP(request,response);
			}else if("addOptions".equals(op)){
				addOptionsOP(request,response);
			}else if("findSubjectByName".equals(op)){
				findSubjectByName(request,response);
			}else if("getSubjectss".equals(op)){
				getSubjectssOp(request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}
	private void getSubjectssOp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		HttpSession session=request.getSession();
		List<Subject> ls=(List<Subject>) session.getAttribute("searchSubject");
		User u=(User) session.getAttribute("User");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("User", u);
		map.put("obj", ls);
		list.add(map);
		super.outJson(list, response);
	}
	private void findSubjectByName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String keywords=request.getParameter("keywords");
		List<Subject> list=sb.findSubjectByName(keywords);
		HttpSession session=request.getSession();
		session.setAttribute("searchSubject", list);
		response.sendRedirect("search.html");
	}
	private void addOptionsOP(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String subid=request.getParameter("subid");
		int optionscount=Integer.parseInt(request.getParameter("optionscount"));
		String[] options=request.getParameterValues("options");
		PrintWriter out=response.getWriter();
		if(optionscount>=options.length){
			out.print("<meta charset='utf-8'><script>alert('你什么都没改...');window.location.href='manage.html'</script>");
			return;
		}else{
			String[]addoptions=new String[options.length-optionscount];
			for(int i=optionscount;i<options.length;i++){
				addoptions[i-optionscount]=options[i];
			}
			sb.addOptions(subid,addoptions);
			out.print("<meta charset='utf-8'><script>alert('修改成功...');window.location.href='manage.html'</script>");
			return;
		}
	}
	private void getSubjectOP(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		Subject sub=(Subject) session.getAttribute("Subject");
		super.outJson(sub, response);
	}
	private void maintenanceOP(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String subjectid=request.getParameter("subjectid");
		HttpSession session=request.getSession();
		try {
			Subject sub=sb.findSubjectById(subjectid);
			session.setAttribute("Subject", sub);
		} catch (Exception e) {
			e.printStackTrace();
			super.outJson("<script>alert('出错了');</script>", response);
		}
		request.getRequestDispatcher("updateOptions.html").forward(request, response);
		
		
		
		
	}
	private void addSubjectOP(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out=response.getWriter();
		String[] options=request.getParameterValues("options");
		String title=request.getParameter("subject.title");
		String type=request.getParameter("subject.type");
		HttpSession session=request.getSession();
		User u=(User) session.getAttribute("User");
		String userid=u.getUserId();
		try {
			sb.addSubject(title,type,userid,options);
		} catch (Exception e) {
			e.printStackTrace();
			out.print("<meta charset='utf-8'><script>alert('增加失败...');window.location.href='add.html'</script>");
		}
		out.print("<meta charset='utf-8'><script>alert('增加成功...');window.location.href='add.html'</script>");
	}
	private void VoteOneOP(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		User u=(User) session.getAttribute("User");
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("User", u);
		Subject sub=(Subject) session.getAttribute("Subject");
		map.put("obj", sub);
		list.add(map);
		super.outJson(list, response);
	}
	private void doSubjectOP(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String subjectid=request.getParameter("subjectid");
		HttpSession session=request.getSession();
		try {
			Subject sub=sb.findSubjectById(subjectid);
			session.setAttribute("Subject", sub);
		} catch (Exception e) {
			e.printStackTrace();
			super.outJson("<script>alert('出错了');</script>", response);
		}
		request.getRequestDispatcher("vote.html").forward(request, response);
	}
	private void selectSubjectOP(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		User u=(User) session.getAttribute("User");
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("User", u);
		List<Subject> li=sb.findSubjectAll();
		map.put("obj", li);
		list.add(map);
		super.outJson(list, response);
		
	}
	
}
