package com.leaderboard.api.entity;

import java.util.HashSet;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.leaderboard.api.enumerations.Badge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
	@Id
	private String userId;
	private String username;
	private int score;
	private HashSet<Badge> badges;
	
	@Transient
    public void updateBadges() {
		if (badges == null) {
	        badges = new HashSet<>();
	    }
        
        if (score >= 1 && score < 30) {
            badges.add(Badge.CODING_NINJA);
        } else if (score >= 30 && score < 60) {
            badges.add(Badge.CODE_CHAMP);
        } else if (score >= 60 && score <= 100) {
            badges.add(Badge.CODE_MASTER);
        }
    }
}
