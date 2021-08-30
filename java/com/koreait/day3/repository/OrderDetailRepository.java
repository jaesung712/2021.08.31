package com.koreait.day3.repository;

import com.koreait.day3.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    Optional<OrderDetail> findFirstByIdOrderByIdDesc(Long id);

    Optional<OrderDetail> findById(Long id);
}
