package com.haui.bookshopwebsite.repository;

import com.haui.bookshopwebsite.entity.User;
import com.haui.bookshopwebsite.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserOrderByCreatedAtDesc(User user);

    Page<Order> findByStatus(String status, Pageable pageable);

    List<Order> findTop10ByOrderByCreatedAtDesc();

    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM Order o where  o.status = 'DELIVERING'")
    BigDecimal sumTotalPrice();

    Optional<Order> findByVnpTxnRef(String vnpTxnRef);
}
