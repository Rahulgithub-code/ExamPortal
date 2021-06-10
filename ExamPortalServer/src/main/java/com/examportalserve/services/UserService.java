package com.examportalserve.services;

import com.examportalserve.model.User;
import com.examportalserve.model.UserRole;

import java.util.Set;

public interface UserService {
    // creating user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    //get user by user name
    public User getUser(String username);

    //delete user by Id
    public void deleteUser(Long userId);

    //update user by Id

}
