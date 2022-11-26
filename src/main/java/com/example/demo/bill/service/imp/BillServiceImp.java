package com.example.demo.bill.service.imp;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bill.entity.Bill;
import com.example.demo.bill.entity.CartDto;
import com.example.demo.bill.entity.CartItem;

import com.example.demo.bill.repository.BillRepository;
import com.example.demo.bill.service.BillService;
import com.example.demo.billDetail.entity.BillDetail;
import com.example.demo.billDetail.repository.BillDetailRepository;
import com.example.demo.cart.CartServiceImp;
import com.example.demo.login.store.entity.User;
import com.example.demo.login.store.reponsitory.UserRepository;
import com.example.demo.product.service.impl.ProductServiceImp;

@Service
@Transactional
public class BillServiceImp implements BillService{
	@Autowired
    private BillRepository billRepository;

    @Autowired
    private CartServiceImp cartService;

    @Autowired
    private ProductServiceImp productService;

    @Autowired
    private BillDetailRepository billDetailRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Bill> getAllBill() {
        return billRepository.findAll();
    }

    @Override
    public void saveBill(User user) {
    	User user2 = userRepository.getById(1);
        CartDto cartDto = cartService.listCartItem(user);
        List<CartItem> cartItemList = cartDto.getCartItems();
        // create the bill and save it
        Bill bill = new Bill();
        bill.setCustomer(user);
        bill.setStaff(user2);
        bill.setTotal( cartDto.getTotalCost());
        bill.setCreateDay(new Date());
        bill.setNote("not thing");
        billRepository.save(bill);
        System.out.println(bill);
        for (CartItem cartItem :
                cartItemList) {
            // create billdetail and save each one
            BillDetail billDetail = new BillDetail();
            billDetail.setProduct(cartItem.getProduct());
            billDetail.setUnitPrice((long) cartItem.getProduct().getProduct().getPrice());
            billDetail.setQuantity(cartItem.getQuantity());
            billDetail.setBill(bill);
            billDetailRepository.save(billDetail);
        }
        productService.reductionQuantity(user);
        cartService.deleteUserCartItems(user);
    }

    @Override
    public String removeBillById(Long id) {
        billRepository.deleteById(id);
        return "bill remove" + id;
    }

    @Override
    public Bill getBillById(Long id) {
        return billRepository.findById(id).get();
    }

    @Override
    public List<Bill> findBillByUserId(Long userId) {
        List<Bill> billList = billRepository.findBillByUserId(userId);
        return billList;
    }
    
    public List<BillDetail> findBillDetailByBillId(Long id) {
        return billDetailRepository.listBillDetailByBillId(id);
    }

}
