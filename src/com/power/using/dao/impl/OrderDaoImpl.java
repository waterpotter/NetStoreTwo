package com.power.using.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.power.using.dao.OrderDao;
import com.power.using.domain.Order;
import com.power.using.domain.OrderItem;
import com.power.using.utils.DBCPUtil;

public class OrderDaoImpl implements OrderDao {

	private QueryRunner qr=new QueryRunner(DBCPUtil.getDataSource());
	
	@Override
	public void save(Order o) {
		
		try {
			qr.update("insert into orders (ordernum,quantity,money,status,customerId) values(?,?,?,?,?)",o.getOrdernum(),
					o.getQuantity(),o.getMoney(),o.getStatus(),o.getCustomer().getId());
			
			List<OrderItem> items = o.getItems();
			for (OrderItem item : items) {
				qr.update("insert into orderitems (id,quantity,price,bookId,ordernum) values (?,?,?,?,?)",item.getId(),
						item.getQuantity(),item.getPrice(),item.getBook().getId(),o.getOrdernum());
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	public Order findByNum(String orderNum) {
		try {
			return qr.query("select * from orders where oedernum=?", new BeanHandler<Order>(Order.class),orderNum);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public List<Order> findByCustomerId(String id) {
		try {
			return qr.query("select * from orders where customerId=? order by ordernum desc", new BeanListHandler<Order>(Order.class),id);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void updateStatus(Order order) {
		try {
			qr.update("update ordes set status=? where ordernum=?",order.getStatus(), order.getOrdernum());
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

}
