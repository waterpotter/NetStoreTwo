package com.power.using.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.power.using.dao.CustomerDao;
import com.power.using.domain.Customer;
import com.power.using.utils.DBCPUtil;

public class CustomerDaoImpl implements CustomerDao {
	
	private QueryRunner qr=new QueryRunner(DBCPUtil.getDataSource());

	@Override
	public void save(Customer c) {
		try {
			qr.update("insert into customer (id,username,password,nickname,phonenum,address,email) values (?,?,?,?,?,?,?)",
					c.getId(),c.getUsername(),c.getPassword(),c.getNickname(),c.getPhonenum(),c.getAddress(),c.getEmail());
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Customer findOne(String customerId) {
		try {
			return qr.query("select * from customer where id=?", new BeanHandler<Customer>(Customer.class),customerId);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Customer find(String username, String password) {
		try {
			return qr.query("select * from customer where username=? and password=?", new BeanHandler<Customer>(Customer.class),username,password);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
