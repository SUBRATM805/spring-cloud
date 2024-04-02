package com.subrat.orderservice.api.repository;

import com.subrat.orderservice.api.common.Payment;
import com.subrat.orderservice.api.entity.Order;
import com.subrat.orderservice.api.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductDetails,Integer> {
}
