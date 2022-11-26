package com.example.demo.bill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.bill.entity.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long>{
	@Query("select b from Bill b where b.customer.id=?1")
	List<Bill> findBillByUserId(Long user_id);
}
