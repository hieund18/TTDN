package com.haui.bookshopwebsite.service.impl;

import com.haui.bookshopwebsite.service.OrderDetailService;
import com.haui.bookshopwebsite.entity.Order;
import com.haui.bookshopwebsite.entity.OrderDetail;
import com.haui.bookshopwebsite.repository.OrderDetailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> getAllOrderDetailByOrder(Order order) {
        return orderDetailRepository.findByOrder(order);
    }
}
