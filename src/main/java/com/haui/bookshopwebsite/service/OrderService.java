package com.haui.bookshopwebsite.service;

import com.haui.bookshopwebsite.dto.CartDTO;
import com.haui.bookshopwebsite.dto.OrderPerson;
import com.haui.bookshopwebsite.entity.User;
import com.haui.bookshopwebsite.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();

    List<Order> getAllOrdersByUser(User user);

    Order getOrderById(Long orderId);

    Order createUnpaidOrder(CartDTO cart, User user, OrderPerson orderPerson);

    Order createOrder(CartDTO cart, User user, OrderPerson orderPerson);

    Order createVNPayOrder(CartDTO cart, User user, OrderPerson orderPerson);

    Order updateOrder(Order order);

    void deleteOrder(Long orderId);

    void cancelOrder(Order order);

    Page<Order> getOrdersByStatus(String status, Pageable pageable);

    Page<Order> getAllOrdersOnPage(Pageable pageable);

    void setProcessingOrder(Order order);

    void setDeliveringOrder(Order order);

    void setReceivedToOrder(Order order);

    List<Order> getTop10orders();

    BigDecimal getTotalRevenue();

    Long countOrder();

    void setUnpaidOrder(Order order, String vnpTxnRef);

    Order getOrderByVnpTxnRef(String vnpTxnRef);

    void updateUnpaidToPending(Order order);
}
