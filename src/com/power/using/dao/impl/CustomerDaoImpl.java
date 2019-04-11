package com.power.using.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.power.using.dao.CustomerDap;
import com.power.using.domain.Customer;
import com.power.using.utils.DBCPUtil;

public class CustomerDaoImpl implements CustomerDap {
	
	private QueryRunner qr=new QueryRunner(DBCPUtil.getDataSource());

	@Override
	public void save(Customer c) {
		try {
			qr.update("");
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Customer findOne(String customerId) {
		return null;
	}

	@Override
	public Customer find(String username, String password) {
		return null;
	}

}
