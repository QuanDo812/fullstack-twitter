package com.twitter.demo.util;

import com.twitter.demo.model.User;

public class UserUtil {

    public static final boolean isRegUser(User reqUser, User user2) {
        return reqUser.getId().equals(user2.getId());
    }

    public static final boolean isFollowedByReqUser(User reqUser, User user2) {
        return reqUser.getFollowings().contains(user2);
    }
}
