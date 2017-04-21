package com.zd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zd.bean.Item;
import com.zd.bean.Subject;
import com.zd.bean.User;
import com.zd.biz.ItemBiz;
import com.zd.biz.impl.ItemBizImpl;
import com.zd.web.model.JsonModel;

public class ItemServlet extends BasicServlet{
	private static final long serialVersionUID = 1L;
	private ItemBiz ib=new ItemBizImpl();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if("selectItem".equals(op)){
				selectItemOP(request,response);
			}else if("addItems".equals(op)){
				addItemsOP(request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	private void selectItemOP(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		Subject sub=(Subject) session.getAttribute("Subject");
		List<Item> item=ib.findItemBySubjectid(sub.getId().toString());
		List<Integer> count=new ArrayList<Integer>();
		for(int i=0;i<sub.getOptions().size();i++){
			count.add(i, 0);
			for(int j=0;j<item.size();j++){
				String[] oid=item.get(j).getOptionid().split(",");
				for(int z=0;z<oid.length;z++){
					if(sub.getOptions().get(i).getId().toString().equals(oid[z])){
						count.set(i, count.get(i)+1);
					}
				}
			}
			
		}
		
		
		List<Object> list=new ArrayList<Object>();
		list.add(sub);
		list.add(count);
		super.outJson(list, response);
	}
	private void addItemsOP(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		Subject sub=(Subject) session.getAttribute("Subject");
		User u=(User) session.getAttribute("User");
		String[] optionid=request.getParameterValues("options");
		
		Item item=new Item();
		String option="";
		for(int i=0;i<optionid.length;i++){
			option+=optionid[i]+",";
		}
		item.setOptionid(option);
		item.setSubjectid(Integer.parseInt(sub.getId().toString()));
		item.setUserid(u.getUserId());
		try {
			ib.addItem(item);
			response.sendRedirect("vote_success.html");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			out.print("<meta charset='utf-8'><script>alert('投票失败...');window.location.href='vote_success.html'</script>");
			return;
		}
		
	}
	
	
	
}
