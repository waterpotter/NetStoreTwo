package com.power.using.dao;

import com.power.using.domain.Book;

public interface BookDao {

	void save(Book book);

	/**
	 * 根据书籍id查询出书籍书籍
	 * @param bookId
	 * @return
	 */
	Book findBookByid(String bookId);

}
