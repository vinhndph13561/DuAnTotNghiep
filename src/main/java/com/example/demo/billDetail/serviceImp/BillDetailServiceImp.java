package com.example.demo.billDetail.serviceImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.billDetail.entity.BillDetail;
import com.example.demo.billDetail.repository.BillDetailRepository;
import com.example.demo.billDetail.service.BillDetailService;


public class BillDetailServiceImp implements BillDetailService{

	@Autowired
    BillDetailRepository billDetailRepository;

    @Override
    public List<BillDetail> getAllBillDetail() {
        return billDetailRepository.findAll();
    }

    @Override
    public BillDetail updateBillDetail(BillDetail billDetail) {
        BillDetail existingBillDetail = billDetailRepository.findById(billDetail.getId()).orElse(null);
        existingBillDetail.setQuantity(billDetail.getQuantity());
        existingBillDetail.setUnitPrice(billDetail.getUnitPrice());
        existingBillDetail.setProduct(billDetail.getProduct());
        return billDetailRepository.save(existingBillDetail);
    }

    @Override
    public BillDetail saveBillDetail(BillDetail billDetail) {
        return billDetailRepository.save(billDetail);
    }


    @Override
    public BillDetail getBillDetailById(Long id) {
        return billDetailRepository.getById(id);
    }

    @Override
    public List<BillDetail> findBillDetailByBillId(Long id) {
        return billDetailRepository.listBillDetailByBillId(id);
    }

    @Override
    public long totalMoney(Long billId){
        return billDetailRepository.totalMoney(billId);
    }

}
