package ir.mostashar.model.user.service;

import ir.mostashar.model.user.User;
import ir.mostashar.model.user.UserPrinciple;
import ir.mostashar.model.user.dto.ListUserDTO;
import ir.mostashar.model.user.dto.UserDTO;
import ir.mostashar.model.user.dto.UserForm;
import ir.mostashar.model.user.repository.UserRepo;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found with -> username or password : " + username)
                );
        return UserPrinciple.build(user);
    }

    public Optional<User> findUserByUid(String uuid) {
        Optional<User> user = userRepo.findByUid(UUID.fromString(uuid));
        if (user.isPresent())
            return Optional.ofNullable(user.get());
        else
            return Optional.empty();

    }

    public boolean existsByUsernameOrNationalId(String username, String nationalId) {
        Optional<Boolean> exists = userRepo.existsByUsernameOrNationalId(username, nationalId);
        if (exists.isPresent())
            return exists.get();
        else
            return false;

    }

    public boolean create(UserForm userForm) {
        Optional<Boolean> exists = userRepo.existsByUsernameOrNationalId(userForm.getUsername(), userForm.getNationalId());
        if (exists.isPresent() && exists.get()) {
            User user = new User();
            user.setUid(UUID.randomUUID());
            user.setFirstName(userForm.getFirstName());
            user.setLastName(userForm.getLastName());
            user.setFatherName(userForm.getFatherName());
            user.setUsername(userForm.getUsername());
            user.setPassword(userForm.getPassword());
            user.setEmail(userForm.getEmail());
            user.setNationalId(userForm.getNationalId());
            user.setBirthDate(userForm.getBirthDate());
            user.setMobileNumber(userForm.getMobileNumber());
            userRepo.save(user);
            return true;
        }
        return false;
    }

    public boolean update(UserForm userForm) {
        Optional<User> user = userRepo.findByUid(UUID.fromString(userForm.getUserid()));
        Optional<Boolean> exists = userRepo.existsByUsernameOrNationalId(userForm.getUsername(), userForm.getNationalId());
        if (exists.isPresent() && exists.get()) {
            if (user.isPresent()) {
                user.get().setFirstName(userForm.getFirstName());
                user.get().setLastName(userForm.getLastName());
                user.get().setFatherName(userForm.getFatherName());
                user.get().setUsername(userForm.getUsername());
                user.get().setPassword(userForm.getPassword());
                user.get().setEmail(userForm.getEmail());
                user.get().setNationalId(userForm.getNationalId());
                user.get().setBirthDate(userForm.getBirthDate());
                user.get().setMobileNumber(userForm.getMobileNumber());
                userRepo.save(user.get());
                return true;
            }
        }
        return false;
    }

    public Optional<User> findById(String id) {
        Optional<User> user = userRepo.findByUid(UUID.fromString(id));
        if (user.isPresent())
            return Optional.ofNullable(user.get());
        else
            return Optional.empty();
    }

    public Optional<UserDTO> findDTOByUserId(String userId) {
        Optional<User> user = userRepo.findByUid(UUID.fromString(userId));
        if (user.isPresent()) {
            UserDTO userDTO = new UserDTO();
            userDTO.setStatus(HttpStatus.OK.value());
            userDTO.setMessage(Constants.KEY_SUCESSE);

            userDTO.setUserid(user.get().getUid().toString());
            userDTO.setFirstName(user.get().getFirstName());
            userDTO.setLastName(user.get().getLastName());
            userDTO.setFatherName(user.get().getFatherName());
            userDTO.setUsername(user.get().getUsername());
            userDTO.setPassword(user.get().getPassword());
            userDTO.setEmail(user.get().getEmail());
            userDTO.setNationalId(user.get().getNationalId());
            userDTO.setBirthDate(user.get().getBirthDate());
            userDTO.setMobileNumber(user.get().getMobileNumber());
            return Optional.ofNullable(userDTO);
        }
        return Optional.empty();
    }

    public Optional<ListUserDTO> findAllDTO(int queryType, String userName_FirstName, String lastName) {
        Optional<List<User>> list = Optional.empty();
        switch (queryType) {
            case 1:
                list = userRepo.findAllByUsernameLike(userName_FirstName);
                break;
            case 2:
                list = userRepo.findAllByFirstNameLikeOrLastNameLike(userName_FirstName, lastName);
                break;
        }
        if (list.isPresent()) {
            ListUserDTO luDTO = new ListUserDTO();
            luDTO.setStatus(HttpStatus.OK.value());
            luDTO.setMessage(Constants.KEY_SUCESSE);
            List<UserDTO> dtoList = new ArrayList<>();
            for (User user : list.get()) {
                UserDTO userDTO = new UserDTO();

                userDTO.setUserid(user.getUid().toString());
                userDTO.setFirstName(user.getFirstName());
                userDTO.setLastName(user.getLastName());
                userDTO.setFatherName(user.getFatherName());
                userDTO.setUsername(user.getUsername());
                userDTO.setPassword(user.getPassword());
                userDTO.setEmail(user.getEmail());
                userDTO.setNationalId(user.getNationalId());
                userDTO.setBirthDate(user.getBirthDate());
                userDTO.setMobileNumber(user.getMobileNumber());
                dtoList.add(userDTO);
            }
            luDTO.setData(dtoList);
            return Optional.ofNullable(luDTO);
        }
        return Optional.empty();
    }


}
