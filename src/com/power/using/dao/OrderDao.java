package com.power.using.dao;

import java.util.List;

import com.power.using.domain.Order;

public interface OrderDao {

	void save(Order o);

	Order findByNum(String orderNum);

	List<Order> findByCustomerId(String id);

	void updateStatus(Order order);

}
