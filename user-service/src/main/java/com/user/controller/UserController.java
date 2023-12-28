package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.model.UserDTO;
import com.user.service.UserService;

@RestController
@RequestMapping("user/")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Value("${test.value}")
	private String testString;

	@PostMapping("create")
	public ResponseEntity<Object> createUser(@RequestBody UserDTO userDTO) {
		return userService.createUser(userDTO);
	}

	@GetMapping("{userID}")
	public ResponseEntity<Object> getUser(@PathVariable String userID) {
		return userService.getUser(userID);
	}
	
	@PutMapping("{userID}")
	public ResponseEntity<Object> updateUser(@PathVariable String userID, @RequestBody UserDTO userDTO) {
		return userService.updateUser(userID, userDTO);
	}

	@GetMapping("fetch")
	public ResponseEntity<Object> getAllUser() {
		return userService.getAllUser();
	}

	@DeleteMapping("remove/{userID}")
	public ResponseEntity<Object> removeUser(@PathVariable String userID) {
		return removeUser(userID);
	}

	@GetMapping("test")
	public String test() {
		return testString;
	}
	
}
