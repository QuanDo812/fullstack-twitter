package com.twitter.demo.controller;

import com.twitter.demo.dto.TwitDto;
import com.twitter.demo.dto.mapper.TwitDtoMapper;
import com.twitter.demo.exception.TwitException;
import com.twitter.demo.exception.UserException;
import com.twitter.demo.model.Twit;
import com.twitter.demo.model.User;
import com.twitter.demo.request.TwitReplyRequest;
import com.twitter.demo.response.ApiResponse;
import com.twitter.demo.service.TwitService;
import com.twitter.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/twits")
@Slf4j
public class TwitController {

    @Autowired
    private TwitService twitService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<TwitDto> createTwit(@RequestBody Twit request
            , @RequestHeader("Authorization") String jwt) throws UserException, TwitException {
        User user = userService.findUserProfileByJwt(jwt);
        Twit twit = twitService.createTwit(request, user);
        TwitDto twitDto = TwitDtoMapper.toTwitDto(twit, user);
        return new ResponseEntity<>(twitDto, HttpStatus.CREATED);
    }

    @PostMapping("/reply")
    public ResponseEntity<TwitDto> replyTwit(@RequestBody TwitReplyRequest request
            , @RequestHeader("Authorization") String jwt) throws UserException, TwitException {
        User user = userService.findUserProfileByJwt(jwt);
        Twit twit = twitService.createReply(request, user);
        TwitDto twitDto = TwitDtoMapper.toTwitDto(twit, user);
        return new ResponseEntity<>(twitDto, HttpStatus.CREATED);
    }

    @PutMapping("/{twitId}/retwit")
    public ResponseEntity<TwitDto> retwit(@PathVariable Long twitId
            , @RequestHeader("Authorization") String jwt) throws UserException, TwitException {
        User user = userService.findUserProfileByJwt(jwt);
        Twit twit = twitService.retwit(twitId, user);
        TwitDto twitDto = TwitDtoMapper.toTwitDto(twit, user);
        return new ResponseEntity<>(twitDto, HttpStatus.OK);
    }

    @GetMapping("/{twitId}")
    public ResponseEntity<TwitDto> findTwitById(@PathVariable Long twitId
            , @RequestHeader("Authorization") String jwt) throws UserException, TwitException {
        User user = userService.findUserProfileByJwt(jwt);
        Twit twit = twitService.findById(twitId);
        TwitDto twitDto = TwitDtoMapper.toTwitDto(twit, user);
        return new ResponseEntity<>(twitDto, HttpStatus.OK);
    }

    @DeleteMapping("/{twitId}")
    public ResponseEntity<ApiResponse> deleteTwit(@PathVariable Long twitId
            , @RequestHeader("Authorization") String jwt) throws UserException, TwitException {
        User user = userService.findUserProfileByJwt(jwt);
        twitService.deleteTwitById(twitId, user.getId());
        ApiResponse response = new ApiResponse();
        response.setMessage("Twit deleted Successfully");
        response.setStatus(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<TwitDto>> getAllTwits(@RequestHeader("Authorization") String jwt)
            throws UserException, TwitException {
        User user = userService.findUserProfileByJwt(jwt);
        log.warn("user: " + user.getEmail());
        List<Twit> twits = twitService.findAllTwits();
        List<TwitDto> twitDtos = TwitDtoMapper.toTwitDtos(twits, user);
        return new ResponseEntity<>(twitDtos, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TwitDto>> getUsersAllTwits(@PathVariable Long userId,
                                                          @RequestHeader("Authorization") String jwt) throws UserException, TwitException {
        User user = userService.findUserProfileByJwt(jwt);
        List<Twit> twits = twitService.getUserTwit(user);
        List<TwitDto> twitDtos = TwitDtoMapper.toTwitDtos(twits, user);
        return new ResponseEntity<>(twitDtos, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/likes")
    public ResponseEntity<List<TwitDto>> findTwitByLikesContainesUser(@PathVariable Long userId
            , @RequestHeader("Authorization") String jwt) throws UserException, TwitException {
        User user = userService.findUserProfileByJwt(jwt);
        List<Twit> twits = twitService.findByLikesContainsUser(user);
        List<TwitDto> twitDtos = TwitDtoMapper.toTwitDtos(twits, user);
        return new ResponseEntity<>(twitDtos, HttpStatus.OK);
    }
}
