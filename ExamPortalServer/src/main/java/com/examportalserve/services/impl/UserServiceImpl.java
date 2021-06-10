package com.examportalserve.services.impl;

import com.examportalserve.helper.UserFoundException;
import com.examportalserve.model.User;
import com.examportalserve.model.UserRole;
import com.examportalserve.repo.RoleRepository;
import com.examportalserve.repo.UserRepository;
import com.examportalserve.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    // creating user
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        // check user if already available.
        User local = this.userRepository.findByUsername(user.getUsername());
        if(local!=null){
            System.out.println("User is already registered.");
            throw new UserFoundException();
        }else{
            // create user
            // save role first
            for(UserRole ur:userRoles){
                roleRepository.save(ur.getRole());
                System.out.println(ur.getRole());
            }
            // then save user
            user.getUserRoles().addAll(userRoles);
            local= this.userRepository.save(user);
        }
        return local;
    }

    // getting user by username
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    // deleting user by id
    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    //Updating user by id
}
