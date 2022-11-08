package com.example.ReadingIsGood.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ReadingIsGood.entity.Customer;
import com.example.ReadingIsGood.entity.Order;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer findByEmail(String email);

	public Customer findByCustomerId(Long id);

	@Query(value = "from Order where customer_id =:customerId")
	public List<Order> findAllByCustomerId(Pageable paging, Long customerId);
}
