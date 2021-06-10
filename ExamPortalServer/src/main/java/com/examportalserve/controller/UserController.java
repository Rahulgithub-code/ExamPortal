package com.examportalserve.controller;

import com.examportalserve.model.Role;
import com.examportalserve.model.User;
import com.examportalserve.model.UserRole;
import com.examportalserve.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // creating user
    @PostMapping("/")
    public User creatUser(@RequestBody User user) throws Exception {
        //Set password as a encrypted using BcryptPassEncoder
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        //add role
        Role role = new Role();
        role.setRoleName("NORMAL");

        // set role and user in userRole
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        // provide userRole as a set
        Set<UserRole> userRolesSet = new HashSet<>();
        userRolesSet.add(userRole);

        return this.userService.createUser(user, userRolesSet);
    }

    // get user by username
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){
        return this.userService.getUser(username);
    }

    //delete user by id
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        this.userService.deleteUser(userId);
    }
}
