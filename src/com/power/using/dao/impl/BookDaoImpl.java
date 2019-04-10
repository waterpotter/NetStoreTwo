package com.power.using.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

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

}
