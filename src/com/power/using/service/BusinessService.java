package com.power.using.service;


import java.util.List;

import com.power.using.commons.Page;
import com.power.using.domain.Book;
import com.power.using.domain.Category;
import com.power.using.domain.Customer;

public interface BusinessService {
	
	/**
	 * 添加分类
	 * @param c
	 */
	void addCategory(Category c);
	
	/**
	 * 查询所有分类
	 * @return
	 */
	List<Category> findAllCategories();
	
	
	/**
	 * 根据id查询分类
	 * @param CategoryId
	 * @return
	 */
	Category findCategoryById(String CategoryId);
	
	/**
	 * 添加一本书
	 * 如果book关联的Category为null,要抛出参数错误异常
	 * @param book
	 */
	void addBook(Book book);
	
	/**
	 * 根据bookid查询一本书
	 * @param bookId
	 * @return
	 */
	Book findBookById(String bookId);
	
	/**
	 * 根据用户要查看的页码,返回封装了所有与分页有关的Page对象数据
	 * @param num
	 * @return
	 */
	Page findBookPageRecords(String num);

	Page findBookPageRecords(String num, String categoryId);
	
	void addCustomer(Customer c);
	
	Customer findCustomer(String customerId);
	
	Customer customerLogin(String username,String password);
	
	
	
	
	
	
	
	
	
	
}
