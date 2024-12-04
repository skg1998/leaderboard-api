package com.leaderboard.api.serviceImpls;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.leaderboard.api.dto.ContestRegistrationDto;
import com.leaderboard.api.dto.UserDto;
import com.leaderboard.api.entity.User;
import com.leaderboard.api.exception.NotFoundException;
import com.leaderboard.api.repository.UserRepository;
import com.leaderboard.api.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	private final UserRepository userRepository;

	@Override
	public List<UserDto> getRegisteredUsers() {
		List<User> registerUsers = userRepository.findAll();
		
		// Sort users by score in descending order
	    registerUsers.sort(Comparator.comparingInt(User::getScore).reversed());
		
		List<UserDto> userDtos = new ArrayList<>();
		registerUsers.forEach(user ->  userDtos.add(mapToDto(user)));
		return userDtos;
	}
	
	@Override
	public UserDto registerUserDetail(String userId) {
		Optional<User> optionalUser = userRepository.findByUserId(userId); 
		User user = optionalUser.orElseThrow(() -> new NotFoundException("User not found for given id"));
		return mapToDto(user);
	}

	@Override
	public void registerUser(ContestRegistrationDto contestRegistrationDto) {
		User contest = new User();
		contest.setUsername(contestRegistrationDto.getUsername());
		userRepository.save(contest);
	}

	@Override
	public void updateScore(String userId, int score) {
		Optional<User> optionalContest = userRepository.findByUserId(userId);
		User user = optionalContest.orElseThrow(() -> new NotFoundException("User not found with given id"));
		user.setScore(score);
		userRepository.save(user);
	}

	@Override
	public void deregisterUser(String userId) {
		Optional<User> optionalContest = userRepository.findByUserId(userId);
		User contest = optionalContest.orElseThrow(() -> new NotFoundException("User not found with given id"));
		userRepository.delete(contest);
	}

	private UserDto mapToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setUsername(user.getUsername());
		userDto.setScore(user.getScore());
		userDto.setBadges(user.getBadges());
		return userDto;
	}
}
