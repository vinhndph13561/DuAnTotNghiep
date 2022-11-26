package com.example.demo.billDetail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.bill.service.imp.BillServiceImp;
import com.example.demo.billDetail.entity.BillDetail;
import com.example.demo.billDetail.serviceImp.BillDetailServiceImp;
import com.example.demo.login.store.reponsitory.UserRepository;


@Controller
@RequestMapping("/api/billdetails")
public class BillDetailController {
	@Autowired
    BillServiceImp billDetailService;
	
    @Autowired
    UserRepository userRepository;

    //view bill Details
    @GetMapping("/billdetail/{billId}")
    @ResponseBody
    public List<BillDetail> billDetail(@PathVariable("billId") Long billId) {
        List<BillDetail> billDetail = billDetailService.findBillDetailByBillId(billId);
        return billDetail;
    }
}
