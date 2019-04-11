package com.power.using.dao;

import com.power.using.domain.Customer;

public interface CustomerDap {

	void save(Customer c);

	Customer findOne(String customerId);

	Customer find(String username, String password);

}
