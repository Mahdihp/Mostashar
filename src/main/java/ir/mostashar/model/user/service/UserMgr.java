package ir.mostashar.model.user.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import ir.mostashar.model.client.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ir.mostashar.model.role.Role;
import ir.mostashar.model.role.repository.RoleRepository;
import ir.mostashar.model.role.service.RoleMgr;
import ir.mostashar.model.user.User;
import ir.mostashar.model.user.repository.UserRepository;

public class UserMgr {


   /* private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMgr roleMgr;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserServiceImpl userServiceImpl;

    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        
        Set<Role> savedRoles = new HashSet<Role>();
        Set<Role> roleSet = user.getRoles();
        if(roleSet != null && !roleSet.isEmpty()){
        	for(Role role : roleSet){
        		
        		Example<Role> example = Example.of(role);
        		Optional<Role> savedRole = roleRepository.findOne(example);
        		if(savedRole != null && savedRole.isPresent()){
        			savedRoles.add(savedRole.get());
        		} else {
        			role.setUid(UUID.randomUUID());
        			savedRoles.add(roleMgr.save(role));
        		}
        	}
        }
        user.setRoles(savedRoles);
        user.setUid(UUID.randomUUID());
        
        return userRepository.save(user);
    }*/

//    public User findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }

//	public List<User> findAll() {
//		return userRepository.findAll();
//	}
	
//	public boolean hasAccess(String featureName) {
//
//		User user = userDetailsServiceImpl.getCurrentUser();
//		if (user != null) {
//
//			Set<Role> roles = user.getRoles();
//			for (Role role : roles) {
//				Set<Feature> features = role.getFeatures();
//				for (Feature feature : features) {
//					if (feature.getName().equals(featureName)) {
//						return true;
//					}
//				}
//			}
//		}
//		return false;
//	}
    
}
