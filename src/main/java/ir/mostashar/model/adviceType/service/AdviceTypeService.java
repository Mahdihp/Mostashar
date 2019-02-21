package ir.mostashar.model.adviceType.service;

import ir.mostashar.model.adviceType.repository.AdviceTypeRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdviceTypeService {


    @Autowired
    AdviceTypeRepo adviceTypeRepo;


}
