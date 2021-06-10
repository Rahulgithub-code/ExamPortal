package com.examportalserve.config;

import com.examportalserve.services.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
       final String requestTokenFromHeader= httpServletRequest.getHeader("Authorization");
        System.out.println(requestTokenFromHeader);
        String username=null;
        String jwtToken=null;
        if(requestTokenFromHeader!=null && requestTokenFromHeader.startsWith("Bearer ")){
            //yes
            jwtToken = requestTokenFromHeader.substring(7);
            try {
                username = this.jwtUtil.extractUsername(jwtToken);
            }catch (ExpiredJwtException e){
                e.printStackTrace();
                System.out.println("Jwt token has expired.");
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Error");
            }

        }else{
            System.out.println("Invalid token, not start with Bearer string.");
        }

        // Validated
        if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(username);
            if(this.jwtUtil.validateToken(jwtToken,userDetails)){
                //token valid
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }else {
            System.out.println("Token is not valid");
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
