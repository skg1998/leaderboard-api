package com.leaderboard.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leaderboard.api.dto.AppResponse;
import com.leaderboard.api.dto.ContestRegistrationDto;
import com.leaderboard.api.dto.SuccessResponse;
import com.leaderboard.api.dto.UserDto;
import com.leaderboard.api.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    
    @GetMapping("/{userId}")
    public ResponseEntity<AppResponse> getUserById(@PathVariable String userId) {
        UserDto userDto = userService.registerUserDetail(userId);
        return responseMaker("fetch user detail successfully", HttpStatus.OK, userDto);  
    }

    @GetMapping("/")
    public ResponseEntity<AppResponse> getAllUsers() {
        List<UserDto> userDtos = userService.getRegisteredUsers();
        return responseMaker("fetch all users detail successfully", HttpStatus.OK, userDtos);  
    }

    @PostMapping
    public ResponseEntity<AppResponse> registerUser(@RequestBody ContestRegistrationDto user) {
        userService.registerUser(user);
        return responseMaker("create user successfully", HttpStatus.OK, null);  
    }

    @PutMapping("/{userId}")
    public ResponseEntity<AppResponse> updateScore(@PathVariable String userId, @RequestParam int score) {
        userService.updateScore(userId, score);
        return responseMaker("update user score successfully", HttpStatus.OK, null);  
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<AppResponse> deleteUser(@PathVariable String userId) {
        userService.deregisterUser(userId);
        return responseMaker("deregister user successfully", HttpStatus.OK, null);
    }
    
    @SuppressWarnings("rawtypes")
	private <T> ResponseEntity<AppResponse> responseMaker(String message, HttpStatus httpStatus, T data){
		@SuppressWarnings("unchecked")
		SuccessResponse<T> successResponse = new SuccessResponse(message, data);
        AppResponse response = new AppResponse(true, successResponse, null);
        return response.toResponseEntity(httpStatus);
	}
}