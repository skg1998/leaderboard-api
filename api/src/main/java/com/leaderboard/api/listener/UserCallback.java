package com.leaderboard.api.listener;

import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;
import org.springframework.stereotype.Component;

import com.leaderboard.api.entity.User;

@Component
public class UserCallback implements BeforeConvertCallback<User> {
    @Override
    public User onBeforeConvert(User user, String collection) {
        user.updateBadges(); // Automatically update badges before saving or updating
        return user;
    }
}
