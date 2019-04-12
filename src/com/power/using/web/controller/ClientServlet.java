package com.power.using.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.power.using.commons.Page;
import com.power.using.constant.Constants;
import com.power.using.domain.Book;
import com.power.using.domain.Category;
import com.power.using.domain.Customer;
import com.power.using.domain.Order;
import com.power.using.domain.OrderItem;
import com.power.using.service.BusinessService;
import com.power.using.service.impl.BusinessServiceImpl;
import com.power.using.utils.IdGenertor;
import com.power.using.utils.WebUtil;
import com.power.using.web.beans.Cart;
import com.power.using.web.beans.CartItem;

public class ClientServlet extends HttpServlet {

	private BusinessService s = new BusinessServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		if ("showIndex".equals(op)) {
			showIndex(request, response);
		} else if ("showCategoryBooks".equals(op)) {
			showCategoryBooks(request, response);
		} else if ("showBookDetial".equals(op)) {
			showBookDetial(request, response);
		} else if ("buy".equals(op)) {
			buy(request, response);
		}else if("changeNum".equals(op)){
			changeNum(request,response);
		}else if("delOneItem".equals(op)){
			delOneItem(request,response);
		}else if("delAllItem".equals(op)){
			delAllItem(request,response);
		}else if("registCustomer".equals(op)){
			registCustomer(request,response);
		}else if("loginCustomer".equals(op)){
			loginCustomer(request,response);
		}else if("logoutCustomer".equals(op)){
			logoutCustomer(request,response);
		}else if("genOrder".equals(op)){
			genOrder(request,response);
			
		}else if("showCustomerOrders".equals(op)){
			showCustomerOrders(request,response);
		}
	}

	private void showCustomerOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
		HttpSession session = request.getSession();
		Customer c=(Customer) session.getAttribute(Constants.CUSTOMER_LOGIN_FLAG);
		if(c==null){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		
		List<Order> os = s.findCustomerOrders(c);
		request.setAttribute("os", os);
		request.getRequestDispatcher("/showCustomerOrders.jsp").forward(request, response);
		
	
	
	}

	private void genOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		Customer c=(Customer) session.getAttribute(Constants.CUSTOMER_LOGIN_FLAG);
		
		if(c==null){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		
		Cart cart=(Cart) session.getAttribute(Constants.HTTPSESSION_CART);
		if(cart==null){
			response.getWriter().write("回话超时!");
			return;
		}
		Order order = new Order();
		order.setOrdernum(IdGenertor.genOrdernum());
		order.setQuantity(cart.getTotalQuantity());
		order.setMoney(cart.getTotalMoney());
		order.setCustomer(c);
		
		List<OrderItem> oItems=new ArrayList<OrderItem>();
		for(Map.Entry<String,CartItem> me:cart.getItems().entrySet()){
			CartItem cItem = me.getValue();
			OrderItem oItem = new OrderItem();
			oItem.setId(IdGenertor.genGUID());
			oItem.setBook(cItem.getBook());
			oItem.setPrice(cItem.getMoney());
			oItem.setQuantity(cItem.getQuantity());
			oItems.add(oItem);
		}
		
		order.setItems(oItems);
		s.genOrder(order);
		request.setAttribute("order", order);
		request.getRequestDispatcher("/pay.jsp").forward(request, response);
		
	}

	private void logoutCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.getSession().removeAttribute(Constants.CUSTOMER_LOGIN_FLAG);
		response.sendRedirect(request.getContextPath());
	
	
	}

	private void loginCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Customer c = s.customerLogin(username, password);
		
		request.getSession().setAttribute(Constants.CUSTOMER_LOGIN_FLAG, c);
		response.sendRedirect(request.getContextPath());
	
	}

	private void registCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Customer c = WebUtil.fillBean(request, Customer.class);
		s.addCustomer(c);
		response.getWriter().write("注册成功!2秒后返回");
		response.setHeader("Refresh", "2;URL="+request.getContextPath());
	}

	private void delAllItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getSession().removeAttribute(Constants.HTTPSESSION_CART);
		response.sendRedirect(request.getContextPath()+"/showCart.jsp");
	}

	private void delOneItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String bookId = request.getParameter("bookId");
		Cart cart=(Cart) request.getSession().getAttribute(Constants.HTTPSESSION_CART);
		cart.getItems().remove(bookId);
		response.sendRedirect(request.getContextPath()+"/showCart.jsp");
	}

	private void changeNum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String num=request.getParameter("num");
		String bookId=request.getParameter("bookId");
		Cart cart=(Cart) request.getSession().getAttribute(Constants.HTTPSESSION_CART);
		CartItem item=cart.getItems().get(bookId);
		item.setQuantity(Integer.parseInt(num));
		response.sendRedirect(request.getContextPath()+"/showCart.jsp");
	}

	private void buy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		Book book = s.findBookById(bookId);
		HttpSession session = request.getSession();
		Cart cart=(Cart) session.getAttribute(Constants.HTTPSESSION_CART);
		if(cart==null){
			cart=new Cart();
			session.setAttribute(Constants.HTTPSESSION_CART, cart);
		}
		
		cart.addBook(book);
		
		response.getWriter().write("购买成功!2秒后返回主页");
		response.setHeader("Refresh","2;URL="+request.getContextPath() );
		
	}

	private void showBookDetial(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		Book book = s.findBookById(bookId);
		request.setAttribute("book", book);
		request.getRequestDispatcher("/bookDetial.jsp").forward(request, response);

	}

	private void showCategoryBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Category> cs = s.findAllCategories();
		request.setAttribute("cs", cs);

		String num = request.getParameter("num");
		String categoryId = request.getParameter("categoryId");
		Page page = s.findBookPageRecords(num, categoryId);
		page.setUrl("/client/ClientServlet?op=showCategoryBooks&categoryId=" + categoryId);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/listBooks.jsp").forward(request, response);

	}

	private void showIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> cs = s.findAllCategories();
		request.setAttribute("cs", cs);
		String num = request.getParameter("num");
		Page page = s.findBookPageRecords(num);
		page.setUrl("/client/ClientServlet?op=showIndex");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/listBooks.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
