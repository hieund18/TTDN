package com.haui.bookshopwebsite.repository;

import com.haui.bookshopwebsite.entity.Order;
import com.haui.bookshopwebsite.entity.OrderDetail;
import com.haui.bookshopwebsite.entity.composite_key.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
    List<OrderDetail> findByOrder(Order order);

    @Query("SELECT od.quantity FROM OrderDetail od WHERE od.order.id = :orderId AND od.book.id = :bookId")
    int findByBookAndOrOrder(Long orderId, Long bookId);

    List<OrderDetail> findByBookId(long bookId);
}
