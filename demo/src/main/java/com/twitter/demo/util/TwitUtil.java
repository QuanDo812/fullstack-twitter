package com.twitter.demo.util;

import com.twitter.demo.model.Like;
import com.twitter.demo.model.Twit;
import com.twitter.demo.model.User;

public class TwitUtil {

    public final static boolean isLikedByRequestUser(User requestUser, Twit twit) {
        for (Like like : twit.getLikes()) {
            if (like.getUser().getId().equals(requestUser.getId())) {
                return true;
            }
        }
        return false;
    }

    public final static boolean isRetwitedByRequestUser(User requestUser, Twit twit) {
        for (User user : twit.getRetwitUsers()) {
            if (user.getId().equals(requestUser.getId())) {
                return true;
            }
        }
        return false;
    }

}
