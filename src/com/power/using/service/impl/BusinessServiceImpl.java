package com.power.using.service.impl;

import java.util.List;

import javax.enterprise.inject.New;

import com.power.using.dao.CategoryDao;
import com.power.using.dao.impl.CategoryDaoImpl;
import com.power.using.domain.Category;
import com.power.using.service.BusinessService;
import com.power.using.utils.IdGenertor;

public class BusinessServiceImpl implements BusinessService{

	private CategoryDao categoryDao=new CategoryDaoImpl();
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

}
