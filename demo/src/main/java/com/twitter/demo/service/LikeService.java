package com.twitter.demo.service;

import com.twitter.demo.exception.TwitException;
import com.twitter.demo.exception.UserException;
import com.twitter.demo.model.Like;
import com.twitter.demo.model.User;

import java.util.List;

public interface LikeService {
    public Like likeTwit(Long twitId, User user) throws UserException, TwitException;

    public List<Like> getAllLikes(Long twitId) throws TwitException;
}
