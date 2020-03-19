package com.example.Shopping.repository;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.Shopping.model.Customer;



public interface CustomerRepository extends JpaRepository<Customer, Long>  {
	List<Customer> findByProductId(long pid);

	@Transactional
	@Modifying
	@Query(value ="insert into customer_product  values(?1,?2)",nativeQuery=true)
	void saveInCustomerProduct(long cid, long pid);
}