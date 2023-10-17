package com.microServices.orderService.Repository;


import com.microServices.orderService.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Long> {
}
