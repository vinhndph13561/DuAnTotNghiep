package com.example.demo.billDetail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.billDetail.entity.BillDetail;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Long>{
//	@Query("select bd from BillDetail bd where bd.")
//	List<BillDetail> findAllByUser(User user);
	List<BillDetail> findBillDetailByBillId(@Param("bill_id") Long bill_id);
	
	@Query(value = "SELECT * FROM billdetail_tbl WHERE bill_id =  ?1", nativeQuery = true)
    List<BillDetail> listBillDetailByBillId(@Param("bill_id") Long bill_id);

    @Query(value = "SELECT SUM(price) FROM bill_detail WHERE bill_id =  ?1", nativeQuery = true)
    long totalMoney(Long billId);
}
