package com.twitter.demo.service.impl;

import com.twitter.demo.config.JwtProvider;
import com.twitter.demo.exception.UserException;
import com.twitter.demo.model.User;
import com.twitter.demo.repository.UserRepository;
import com.twitter.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserById(Long userId) throws UserException {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserException("User not found with id " + userId));
        return user;
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserException("User not found with email " + email);
        }
        return user;
    }

    @Override
    public User updateUser(Long userId, User request) throws UserException {
        User user = findUserById(userId);
        if (request.getFullName() != null) {
            user.setFullName(request.getFullName());
        }
        if (request.getImage() != null) {
            user.setImage(request.getImage());
        }
        if (request.getBackgroundImage() != null) {
            user.setBackgroundImage(request.getBackgroundImage());
        }
        if (request.getBirthDate() != null) {
            user.setBirthDate(request.getBirthDate());
        }
        if (request.getLocation() != null) {
            user.setLocation(request.getLocation());
        }
        if (request.getBio() != null) {
            user.setBio(request.getBio());
        }
        if (request.getWebsite() != null) {
            user.setWebsite(request.getWebsite());
        }
        return userRepository.save(user);
    }

    @Override
    public User followUser(Long userId, User user) throws UserException {
        User followToUser = findUserById(userId);
        if (user.getFollowings().contains(followToUser) && followToUser.getFollowers().contains(user)) {
            user.getFollowings().remove(followToUser);
            followToUser.getFollowers().remove(user);
        } else {
            user.getFollowings().add(followToUser);
            followToUser.getFollowers().add(user);
        }
        userRepository.save(followToUser);
        userRepository.save(user);
        return followToUser;
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }
}
