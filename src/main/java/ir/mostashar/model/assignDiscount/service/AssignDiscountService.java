package ir.mostashar.model.assignDiscount.service;

import ir.mostashar.model.assignDiscount.dto.AssignDiscountForm;
import ir.mostashar.model.assignDiscount.repository.AssignDiscountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignDiscountService {

    @Autowired
    AssignDiscountRepo adRepo;

    public void createAssignDiscount(AssignDiscountForm adForm){

    }
}
