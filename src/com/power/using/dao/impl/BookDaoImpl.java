package com.power.using.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.power.using.dao.BookDao;
import com.power.using.domain.Book;
import com.power.using.domain.Category;
import com.power.using.utils.DBCPUtil;

public class BookDaoImpl implements BookDao {

	private QueryRunner qr=new QueryRunner(DBCPUtil.getDataSource());
	
	@Override
	public void save(Book book) {
		
		try {
			qr.update("insert into books(id,name,author,price,path,filename,description,categoryId) "
					+ "values(?,?,?,?,?,?,?,?)",book.getId(),book.getName(),book.getAuthor(),book.getPrice(),
					book.getPath(),book.getFilename(),book.getDescription(),book.getCategory().getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public Book findBookByid(String bookId) {
		
		try {
			Book book = qr.query("select * from books where id=?", new BeanHandler<Book>(Book.class),bookId);
			if(book!=null){
				Category category = qr.query("select * from categorys where id=(select categoryId from books where id=?)", new BeanHandler<Category>(Category.class),bookId);
				book.setCategory(category);
			}
			return book;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		
		
	}

	@Override
	public int getTotalRecordsNum() {
		try {
			Object obj=qr.query("select count(*) from books", new ScalarHandler(1));
			Long num=(Long)obj;
			return num.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	public List findPageRecords(int startIndex, int pageSize) {
		try {
			List<Book> books = qr.query("select * from books limit ?,?", new BeanListHandler<Book>(Book.class),startIndex,pageSize);
			if(books!=null&&books.size()>0){
				for (Book book : books) {
					Category category = qr.query("select * from categorys where id=(select categoryId from books where id=?)", new BeanHandler<Category>(Category.class),book.getId());
					book.setCategory(category);
				}
				
			}
			return books;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public int getTotalRecordsNum(String categoryId) {
		try {
			Object obj=qr.query("select count(*) from books where categoryId=?", new ScalarHandler(1),categoryId);
			Long num=(Long)obj;
			return num.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public List findPageRecords(int startIndex, int pageSize, String categoryId) {
		try {
			List<Book> books = qr.query("select * from books where categoryId=? limit ?,?", new BeanListHandler<Book>(Book.class),categoryId,startIndex,pageSize);
			if(books!=null&&books.size()>0){
				for (Book book : books) {
					Category category = qr.query("select * from categorys where id=?", new BeanHandler<Category>(Category.class),categoryId);
					book.setCategory(category);
				}
				
			}
			return books;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
