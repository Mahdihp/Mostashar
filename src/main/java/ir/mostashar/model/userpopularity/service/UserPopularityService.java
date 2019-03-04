package ir.mostashar.model.userpopularity.service;

import ir.mostashar.model.client.service.UserServiceImpl;
import ir.mostashar.model.user.User;
import ir.mostashar.model.userpopularity.UserPopularity;
import ir.mostashar.model.userpopularity.dto.ListUserPopularityDTO;
import ir.mostashar.model.userpopularity.dto.UserPopularityDTO;
import ir.mostashar.model.userpopularity.repository.UserPopularityRepo;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserPopularityService {


    @Autowired
    UserPopularityRepo upRepo;

    @Autowired
    UserServiceImpl userService;

    public boolean createUserPopularity(String userUid, String userPopularUid) {

        Optional<User> masteruser = userService.findUserByUid(userUid);
        Optional<User> userpopular = userService.findUserByUid(userPopularUid);

        if (masteruser.isPresent() && userpopular.isPresent()) {
            UserPopularity userPopularity = new UserPopularity();
            userPopularity.setUser(masteruser.get());
            userPopularity.setUserPopu(userpopular.get());
            upRepo.save(userPopularity);
            return true;
        }
        return false;
    }

    public boolean existsPopu(String userUid, String userPopularUid) {
        Optional<UserPopularity> user = upRepo.findByUserUidAndUserPopuUid(UUID.fromString(userUid), UUID.fromString(userPopularUid));
        if (user.isPresent())
            return true;
        else
            return false;
    }


    public boolean saveUserPopularity(UserPopularity userPopularity) {
        if (userPopularity != null) {
            upRepo.save(userPopularity);
            return true;
        }
        return false;
    }

    public boolean deleteUserPopularity(String userUid, String userPopularUid) {
        Optional<User> user = userService.findUserByUid(userUid);
        Optional<User> userPopular = userService.findUserByUid(userPopularUid);

        if (user.isPresent() && userPopular.isPresent()) {
            Optional<UserPopularity> byUserPopular = upRepo.findByUserPopu(userPopular.get().getUid());
            if (byUserPopular.isPresent()) {
                upRepo.delete(byUserPopular.get());
                return true;
            }
        }
        return false;
    }

    public Optional<UserPopularityDTO> findUserPopularityDTOByUserPopular(String uPopular) {
        Optional<User> userPopular = userService.findUserByUid(uPopular);
        if (userPopular.isPresent()) {
            Optional<UserPopularity> byUserPopular = upRepo.findByUserPopu(userPopular.get().getUid());
            if (byUserPopular.isPresent()) {
                UserPopularityDTO userPopularityDTO = new UserPopularityDTO();
                userPopularityDTO.setStatus(HttpStatus.OK.value());
                userPopularityDTO.setMessage(Constants.KEY_SUCESSE);

//                userPopularityDTO.setUserId(byUserPopular.get().getUser().getFactorId().toString());
                userPopularityDTO.setUserPopularId(byUserPopular.get().getUserPopu().getUid().toString());
                return Optional.ofNullable(userPopularityDTO);
            }
        }
        return Optional.empty();
    }

    public Optional<ListUserPopularityDTO> findListUserPopularityDTODTOByUser(String user) {
        Optional<User> userOptional = userService.findUserByUid(user);
        Optional<List<UserPopularity>> allByUser = upRepo.findAllByUser(userOptional.get());
        if (allByUser.isPresent()) {
            ListUserPopularityDTO lupDTO = new ListUserPopularityDTO();
            lupDTO.setStatus(HttpStatus.OK.value());
            lupDTO.setMessage(Constants.KEY_SUCESSE);

            List<UserPopularityDTO> dtoList = new ArrayList<>();
            for (UserPopularity userPopularity : allByUser.get()) {
                UserPopularityDTO userPopularityDTO = new UserPopularityDTO();
//                userPopularityDTO.setUserId(userPopularity.getUser().getFactorId().toString());
                userPopularityDTO.setUserPopularId(userPopularity.getUserPopu().getUid().toString());
                dtoList.add(userPopularityDTO);
            }
            lupDTO.setData(dtoList);
            return Optional.ofNullable(lupDTO);
        }

        return Optional.empty();
    }
}
