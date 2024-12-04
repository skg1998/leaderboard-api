package com.leaderboard.api.dto;

import java.util.HashSet;

import com.leaderboard.api.enumerations.Badge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private String userId;
	private String username;
	private int score;
	private HashSet<Badge> badges;
}
