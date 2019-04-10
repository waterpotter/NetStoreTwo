package com.power.using.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.power.using.commons.Page;
import com.power.using.domain.Book;
import com.power.using.domain.Category;
import com.power.using.service.BusinessService;
import com.power.using.service.impl.BusinessServiceImpl;
import com.power.using.utils.IdGenertor;
import com.power.using.utils.WebUtil;

public class ManageServlet extends HttpServlet {

	private BusinessService s = new BusinessServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		if ("addCategory".equals(op)) {
			addCategory(request, response);
		} else if ("showAllCategory".equals(op)) {
			showAllCategory(request, response);
		} else if ("addBookUI".equals(op)) {
			addBookUI(request, response);
		} else if ("addBook".equals(op)) {
			addBook(request, response);
		}else if("showPageBooks".equals(op)){
			showPageBooks(request,response);
		}
	}

	private void showPageBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("num");
		Page page = s.findBookPageRecords(num);
		page.setUrl("/manage/ManageServlet?op=showPageBooks");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manage/listBooks.jsp").forward(request, response);
		
		
	}

	private void addBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			throw new RuntimeException("the form is not multipart/form-data");
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		List<FileItem> items = new ArrayList<FileItem>();
		try {
			items = sfu.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Book book = new Book();
		for (FileItem item : items) {
			if (item.isFormField()) {
				processFormFiled(item, book);
			} else {
				processUploadFile(item,book);
			}
		}
		
		//book.setCategory(s.findCategoryById(request.getParameter("categoryId")));
		s.addBook(book);
		response.sendRedirect(request.getContextPath()+"/common/message.jsp");
		
		
		
	}

	private void processFormFiled(FileItem item, Book book) {
		try {
			String fieldName = item.getFieldName();
			String filedValue = item.getString("UTF-8");
			BeanUtils.setProperty(book, fieldName, filedValue);
			
			if("categoryId".equals(fieldName)){
				book.setCategory(s.findCategoryById(filedValue));
			}
			
		} catch (UnsupportedEncodingException | IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void processUploadFile(FileItem item,Book book) {
		String storeDirectory = getServletContext().getRealPath("/images");
		File rootDirectory = new File(storeDirectory);
		if (!rootDirectory.exists()) {
			rootDirectory.mkdirs();
		}
		String fileName = item.getName();
		if (fileName != null) {
			fileName = IdGenertor.genGUID() + "." + FilenameUtils.getExtension(fileName);
			book.setFilename(fileName);
		}
		
		String path = genChildDirectory(storeDirectory, fileName);
		book.setPath(path);
		
		try {
			item.write(new File(rootDirectory,path+"/"+fileName));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	private String genChildDirectory(String realPath, String fileName) {

		int hashCode = fileName.hashCode();
		int dir1 = hashCode & 0xf;
		int dir2 = (hashCode & 0xf0) >> 4;

		String str = dir1 + File.separator + dir2;
		File file = new File(realPath, str);
		if (!file.exists()) {
			file.mkdirs();
		}
		return str;
	}

	private void addBookUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> cs = s.findAllCategories();
		request.setAttribute("cs", cs);
		request.getRequestDispatcher("/manage/addBook.jsp").forward(request, response);

	}

	private void showAllCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Category> categories = s.findAllCategories();
		request.setAttribute("cs", categories);
		request.getRequestDispatcher("/manage/listCategory.jsp").forward(request, response);
	}

	private void addCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Category c = WebUtil.fillBean(request, Category.class);
		s.addCategory(c);
		response.sendRedirect(request.getContextPath() + "/common/message.jsp");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
