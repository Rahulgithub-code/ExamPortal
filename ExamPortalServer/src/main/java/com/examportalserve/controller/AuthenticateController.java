package com.examportalserve.controller;

import com.examportalserve.config.JwtUtil;
import com.examportalserve.helper.UserFoundException;
import com.examportalserve.helper.UserNotFoundException;
import com.examportalserve.model.JwtRequest;
import com.examportalserve.model.JwtResponse;
import com.examportalserve.model.User;
import com.examportalserve.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private JwtUtil jwtUtil;

    private void authenticate(String username, String password) throws Exception {
        try {
            System.out.println(username+" "+password);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException e){
            throw new Exception("User Disabled");
        }catch (BadCredentialsException e){
            throw new Exception("Invalid Credentials"+e.getMessage());
        }
    }
    // Generate Token API
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try{
            this.authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw new UserNotFoundException();
        }

        /////////////Authenticated
        UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){
       return ((User) this.userDetailsServiceImpl.loadUserByUsername(principal.getName()));
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex){
        return ResponseEntity.ok( new UserFoundException());
    }
}
