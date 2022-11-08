package com.example.ReadingIsGood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ReadingIsGood.entity.Order;

@Repository
public interface StatisticsRepository extends JpaRepository<Order, Long> {

	@Query(value = "select to_char(o.created_at, 'Month'), count(o.order_id), sum(map.number_of_book), CAST(CAST(sum(b.price*map.number_of_book)  AS FLOAT) AS bigint) "
			+ "from orders o, order_book_map map, book b "
			+ "where map.book_id = b.book_id "
			+ "and map.order_id = o.order_id "
			+ "and o.customer_id =:customerId "
			+ "group by to_char(o.created_at, 'Month')"
			+ "", nativeQuery = true)
	List<Object> getMonthlyStatistic(Long customerId);


}
