package com.example.demo.discount.serviceImp;

import com.example.demo.discount.entity.Discount;
import com.example.demo.discount.repository.DiscountRepository;
import com.example.demo.discount.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountServiceImp implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;



    @Override
    public List<Discount> getAllDiscount() {
        return  discountRepository.findAll();
    }

    @Override
    public Discount getDiscountById(Long id) {
        return discountRepository.findById(id).get();
    }

    @Override
    public Discount updateDiscountById(Discount newDiscount) {

			return discountRepository.save(newDiscount);
    }

    @Override
    public Discount saveDiscount(Discount newDiscount) {
        return discountRepository.save(newDiscount);
    }
}
