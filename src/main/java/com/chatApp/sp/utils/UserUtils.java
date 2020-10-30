package com.chatApp.sp.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.chatApp.sp.model.DBUser;
import com.chatApp.sp.repository.UserRepository;

@Component
public class UserUtils {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	CookieUtils cookieUtils;
	
	
	public String createUser(String email, String userName, String password, String age, int gender){
		
		if(userRepo.findByEmail(email) == null) {
			DBUser user = new DBUser(email, passwordEncoder.encode(password), age, gender, userName);
			
			userRepo.insert(user);
			
			return "SUCCEED";
		}		
		return null;
	}
	
	public DBUser viewUserProfile(String email) {
		DBUser user = userRepo.findByEmail(email);
		
		if(user != null) {
			user.setPassword("");
			return user;
		}
		
		return null;
	}
	
	public String updateUserProfile(String password, String userName, String age, HttpServletRequest req) {
		
		String email = cookieUtils.getEmail(req);
		
		DBUser user = userRepo.findByEmail(email);
		
		if(user != null) {
			if(password != null)
				user.setPassword(passwordEncoder.encode(password));
			if(userName != null && !userName.equals(user.getUserName()))
				user.setUserName(userName);
			if(age != null && age.equals(user.getAge()))
				user.setAge(age);
			userRepo.save(user);
			return "SUCCEED";
		}
		return null;
	}
	
	public String deleteAccount(HttpServletRequest req) {
		String email = cookieUtils.getEmail(req);
		
		DBUser user = userRepo.findByEmail(email);
		
		if(user != null) {
			userRepo.delete(user);
			return "SUCCEED";
		}
		
		return null;
	}
	
}