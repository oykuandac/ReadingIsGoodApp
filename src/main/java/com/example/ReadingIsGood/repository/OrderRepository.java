package com.example.ReadingIsGood.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ReadingIsGood.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	Object findByOrderId(Long id);

	List<Order> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

	@Query("from Order where created_at >=:startDate and created_at<=:endDate")
	List<Order> findByCreatedAtBetween(Date startDate, Date endDate);
}
