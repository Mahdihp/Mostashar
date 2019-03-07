package ir.mostashar.model.user.service;

import ir.mostashar.model.client.service.UserPrinciple;
import ir.mostashar.model.user.User;
import ir.mostashar.model.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}
