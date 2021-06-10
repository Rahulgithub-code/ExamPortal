package com.examportalserve;

import com.examportalserve.model.Role;
import com.examportalserve.model.User;
import com.examportalserve.model.UserRole;
import com.examportalserve.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamPortalServerApplication implements CommandLineRunner {
	@Autowired
	 private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ExamPortalServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//
//		System.out.println("Application is Started . . . .");
//		User user = new User();
//		user.setUsername("rahul");
//		user.setPassword("rahul");
//		user.setFirstName("Rahul");
//		user.setLastName("Kumar");
//		user.setEmail("Rahulkr.mits@gmail.com");
//		user.setProfile("Default.png");
//
//		Role role1 = new Role();
//		role1.setRoleName("Admin");
//		Set<UserRole> userRoleSet = new HashSet<>();
//		UserRole userRole = new UserRole();
//		userRole.setUser(user);
//		userRole.setRole(role1);
//		userRoleSet.add(userRole);
//		User user1 = this.userService.createUser(user, userRoleSet);
//		System.out.println(user1.getUsername());
	}
}
