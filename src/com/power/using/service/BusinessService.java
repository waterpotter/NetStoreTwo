package com.power.using.service;


import java.util.List;

import com.power.using.domain.Category;

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
	
	
	
	
	
	
	
	
	
	
	
	
}
