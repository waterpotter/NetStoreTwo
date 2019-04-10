package com.power.using.service.impl;

import java.util.List;

import javax.enterprise.inject.New;

import com.power.using.commons.Page;
import com.power.using.dao.BookDao;
import com.power.using.dao.CategoryDao;
import com.power.using.dao.impl.BookDaoImpl;
import com.power.using.dao.impl.CategoryDaoImpl;
import com.power.using.domain.Book;
import com.power.using.domain.Category;
import com.power.using.service.BusinessService;
import com.power.using.utils.IdGenertor;

public class BusinessServiceImpl implements BusinessService{

	private CategoryDao categoryDao=new CategoryDaoImpl();
	
	private BookDao bookDao=new BookDaoImpl();
	@Override
	public void addCategory(Category c) {
		c.setId(IdGenertor.genGUID());
		categoryDao.save(c);
	}

	@Override
	public List<Category> findAllCategories() {
		return categoryDao.findAll();
	}

	@Override
	public Category findCategoryById(String categoryId) {
		return categoryDao.findById(categoryId);
	}

	@Override
	public void addBook(Book book) {
		if(book==null){
			throw new IllegalArgumentException("book cannot be null");
		}
		if(book.getCategory()==null){
			throw new IllegalArgumentException("book's category cannot be null");
		}
		
		book.setId(IdGenertor.genGUID());
		bookDao.save(book);
		
	}

	@Override
	public Book findBookById(String bookId) {
		return bookDao.findBookByid(bookId);
	}

	@Override
	public Page findBookPageRecords(String num) {
		return null;
	}

}
