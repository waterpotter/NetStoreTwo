package com.power.using.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.power.using.domain.Category;
import com.power.using.service.BusinessService;
import com.power.using.service.impl.BusinessServiceImpl;
import com.power.using.utils.WebUtil;

public class ManageServlet extends HttpServlet {

	private BusinessService s=new BusinessServiceImpl(); 
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		if("addCategory".equals(op)){
			addCategory(request,response);
		}else if("showAllCategory".equals(op)){
			showAllCategory(request,response);
		}
	}

	private void showAllCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		List<Category> categories = s.findAllCategories();
		request.setAttribute("cs", categories);
		request.getRequestDispatcher("/manage/listCategory.jsp").forward(request, response);
	}

	private void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Category c = WebUtil.fillBean(request, Category.class);
		s.addCategory(c);
		response.sendRedirect(request.getContextPath()+"/common/message.jsp");
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);	
	}

}
