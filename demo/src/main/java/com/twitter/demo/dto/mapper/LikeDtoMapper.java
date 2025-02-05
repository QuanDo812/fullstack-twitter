package com.twitter.demo.dto.mapper;

import com.twitter.demo.dto.LikeDto;
import com.twitter.demo.dto.TwitDto;
import com.twitter.demo.dto.UserDto;
import com.twitter.demo.model.Like;
import com.twitter.demo.model.User;

import java.util.ArrayList;
import java.util.List;

public class LikeDtoMapper {

    public static LikeDto toLikeDto(Like like, User requestUser) {
        UserDto user = UserDtoMapper.toUserDto(like.getUser());
        TwitDto twit = TwitDtoMapper.toTwitDto(like.getTwit(), requestUser);

        LikeDto likeDto = new LikeDto();
        likeDto.setId(like.getId());
        likeDto.setTwit(twit);
        likeDto.setUser(user);
        return likeDto;
    }

    public static List<LikeDto> toLikeDtos(List<Like> likes, User requestUser) {
        List<LikeDto> likeDtos = new ArrayList<>();
        for (Like like : likes) {
            UserDto user = UserDtoMapper.toUserDto(like.getUser());
            TwitDto twit = TwitDtoMapper.toTwitDto(like.getTwit(), requestUser);
            LikeDto likeDto = new LikeDto();
            likeDto.setId(like.getId());
            likeDto.setTwit(twit);
            likeDto.setUser(user);
            likeDtos.add(likeDto);
        }
        return likeDtos;
    }
}