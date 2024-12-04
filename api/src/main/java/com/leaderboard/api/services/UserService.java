package com.leaderboard.api.services;

import java.util.List;

import com.leaderboard.api.dto.ContestRegistrationDto;
import com.leaderboard.api.dto.UserDto;

public interface UserService {
	public List<UserDto> getRegisteredUsers();
	public void registerUser(ContestRegistrationDto contestRegistrationDto);
	public void updateScore(String userId, int score);
	public void deregisterUser(String userId);
	public UserDto registerUserDetail(String userId);
}
