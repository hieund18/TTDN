package com.haui.bookshopwebsite.service;

import com.haui.bookshopwebsite.entity.Order;
import com.haui.bookshopwebsite.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getAllOrderDetailByOrder(Order order);
}
