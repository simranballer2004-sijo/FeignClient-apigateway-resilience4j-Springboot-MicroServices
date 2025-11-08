package com.order.service;

import java.util.List;

import com.order.entity.Orders;

public interface OrderService {
  List<Orders> getAllOrders();
  Orders createOrder(Orders neworder);
}
