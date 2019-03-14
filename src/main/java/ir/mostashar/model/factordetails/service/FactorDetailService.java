package ir.mostashar.model.factordetails.service;

import ir.mostashar.model.factor.Factor;
import ir.mostashar.model.factor.service.FactorService;
import ir.mostashar.model.factordetails.FactorDetail;
import ir.mostashar.model.factordetails.dto.FactorDetailDTO;
import ir.mostashar.model.factordetails.dto.FactorDetailForm;
import ir.mostashar.model.factordetails.dto.ListFactorDetailDTO;
import ir.mostashar.model.factordetails.repository.FactorDetailRepo;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FactorDetailService {


    @Autowired
    private FactorDetailRepo factorDetailRepo;

    @Autowired
    private FactorService factorService;

    public boolean create(FactorDetailForm fdForm) {
        Optional<Factor> factor = factorService.findByUid(fdForm.getFactorId());
        if (factor.isPresent()) {
            FactorDetail factorDetail = new FactorDetail();
            factorDetail.setUid(UUID.randomUUID());
            factorDetail.setItemName(fdForm.getItemName());
            factorDetail.setCountItem(fdForm.getCountItem());
            factorDetail.setPricePer(fdForm.getPricePer());
            factorDetail.setTotalPrice(fdForm.getTotalPrice());
            factorDetail.setDescription(fdForm.getDescription());
            factorDetail.setFactor(factor.get());
            factorDetailRepo.save(factorDetail);
            return true;
        }
        return false;
    }

    public boolean update(FactorDetailForm fdForm) {
        Optional<FactorDetail> factorDetail = factorDetailRepo.findByUid(UUID.fromString(fdForm.getFactorDetailId()));
        if (factorDetail.isPresent()) {
            factorDetail.get().setItemName(fdForm.getItemName());
            factorDetail.get().setCountItem(fdForm.getCountItem());
            factorDetail.get().setPricePer(fdForm.getPricePer());
            factorDetail.get().setTotalPrice(fdForm.getTotalPrice());
            factorDetail.get().setDescription(fdForm.getDescription());
            factorDetailRepo.save(factorDetail.get());
            return true;
        }
        return false;
    }

    public Optional<FactorDetail> findById(String factorDetailId) {
        Optional<FactorDetail> factorDetail = factorDetailRepo.findByUid(UUID.fromString(factorDetailId));
        if (factorDetail.isPresent())
            return Optional.ofNullable(factorDetail.get());
        else
            return Optional.empty();
    }

    public Optional<FactorDetailDTO> findDTOByFactorId(String factorDetailId) {
        Optional<FactorDetail> factorDetail = factorDetailRepo.findByUid(UUID.fromString(factorDetailId));
        if (factorDetail.isPresent()) {
            FactorDetailDTO dto = new FactorDetailDTO();
            dto.setStatus(HttpStatus.OK.value());
            dto.setMessage(Constants.KEY_SUCESSE);

            dto.setFactorDetailId(factorDetail.get().getUid().toString());
            dto.setItemName(factorDetail.get().getItemName());
            dto.setCountItem(factorDetail.get().getCountItem());
            dto.setPricePer(factorDetail.get().getPricePer());
            dto.setTotalPrice(factorDetail.get().getTotalPrice());
            dto.setDescription(factorDetail.get().getDescription());
            dto.setFactorId(factorDetail.get().getFactor().getUid().toString());
            return Optional.ofNullable(dto);
        }
        return Optional.empty();
    }

    public Optional<ListFactorDetailDTO> findAllDTO(int queryType, String factorDetailId) {
        Optional<List<FactorDetail>> list = Optional.empty();
        switch (queryType) {
            case 1:
                list = factorDetailRepo.findAllByFactorUid(UUID.fromString(factorDetailId));
                break;
        }

        if (list.isPresent()) {
            ListFactorDetailDTO lfdDTO = new ListFactorDetailDTO();
            lfdDTO.setStatus(HttpStatus.OK.value());
            lfdDTO.setMessage(Constants.KEY_SUCESSE);
            List<FactorDetailDTO> dtoList = new ArrayList<>();
            for (FactorDetail factorDetail : list.get()) {
                FactorDetailDTO dto = new FactorDetailDTO();
                dto.setFactorDetailId(factorDetail.getUid().toString());
                dto.setItemName(factorDetail.getItemName());
                dto.setCountItem(factorDetail.getCountItem());
                dto.setPricePer(factorDetail.getPricePer());
                dto.setTotalPrice(factorDetail.getTotalPrice());
                dto.setDescription(factorDetail.getDescription());
                dto.setFactorId(factorDetail.getFactor().getUid().toString());
                dtoList.add(dto);
            }
            lfdDTO.setData(dtoList);
            return Optional.ofNullable(lfdDTO);
        }
        return Optional.empty();
    }


}
