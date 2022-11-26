package com.example.demo.bill.service;

import java.util.List;

import com.example.demo.bill.entity.Bill;
import com.example.demo.login.store.entity.User;


public interface BillService {
	List<Bill> getAllBill();

    void saveBill(User user);

    String removeBillById(Long id);

    Bill getBillById(Long id);

    List<Bill> findBillByUserId(Long userId);
}
