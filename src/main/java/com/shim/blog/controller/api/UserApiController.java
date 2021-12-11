package com.shim.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shim.blog.dto.ResponseDto;
import com.shim.blog.model.RoleType;
import com.shim.blog.model.User;
import com.shim.blog.service.UserService;


@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder encode;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto <Integer> save(@RequestBody User user) {
		System.out.println("UserApiController: save 호출됨");
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}	
}
