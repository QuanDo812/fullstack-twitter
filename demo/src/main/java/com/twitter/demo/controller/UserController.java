package com.twitter.demo.controller;

import com.twitter.demo.dto.UserDto;
import com.twitter.demo.dto.mapper.UserDtoMapper;
import com.twitter.demo.exception.UserException;
import com.twitter.demo.model.User;
import com.twitter.demo.service.UserService;
import com.twitter.demo.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserProfile(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        UserDto userDto = UserDtoMapper.toUserDto(user);
        userDto.setReg_user(true);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId
            , @RequestHeader("Authorization") String jwt) throws UserException {
        User requestUser = userService.findUserProfileByJwt(jwt);
        User user = userService.findUserById(userId);
        UserDto userDto = UserDtoMapper.toUserDto(user);
        userDto.setReg_user(UserUtil.isRegUser(requestUser, user));
        userDto.setFollowed(UserUtil.isFollowedByReqUser(requestUser, user));
        return new ResponseEntity<UserDto>(userDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> searchUser(@RequestParam String query
            , @RequestHeader("Authorization") String jwt) throws UserException {
        User requestUser = userService.findUserProfileByJwt(jwt);
        List<User> users = userService.searchUser(query);
        List<UserDto> userDtos = UserDtoMapper.toUserDtos(users);
        return new ResponseEntity<>(userDtos, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody User request
            , @RequestHeader("Authorization") String jwt) throws UserException {
        User requestUser = userService.findUserProfileByJwt(jwt);
        User user = userService.updateUser(requestUser.getId(), request);
        UserDto userDto = UserDtoMapper.toUserDto(user);
        userDto.setReg_user(UserUtil.isRegUser(requestUser, user));
        userDto.setFollowed(UserUtil.isFollowedByReqUser(requestUser, user));
        return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{userId}/follow")
    public ResponseEntity<UserDto> followeUser(@PathVariable Long userId
            , @RequestHeader("Authorization") String jwt) throws UserException {
        User requestUser = userService.findUserProfileByJwt(jwt);
        User user = userService.followUser(userId, requestUser);
        UserDto userDto = UserDtoMapper.toUserDto(user);
        userDto.setFollowed(UserUtil.isFollowedByReqUser(requestUser, user));

        return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
    }
}
