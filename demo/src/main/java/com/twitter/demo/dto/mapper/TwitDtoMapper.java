package com.twitter.demo.dto.mapper;

import com.twitter.demo.dto.TwitDto;
import com.twitter.demo.dto.UserDto;
import com.twitter.demo.model.Twit;
import com.twitter.demo.model.User;
import com.twitter.demo.util.TwitUtil;

import java.util.ArrayList;
import java.util.List;

public class TwitDtoMapper {

    public static TwitDto toTwitDto(Twit twit, User requestUser) {
        UserDto user = UserDtoMapper.toUserDto(twit.getUser());
        boolean isLiked = TwitUtil.isLikedByRequestUser(requestUser, twit);
        boolean isRetwited = TwitUtil.isRetwitedByRequestUser(requestUser, twit);

        List<Long> retwitUserId = new ArrayList<>();
        for (User user1 : twit.getRetwitUsers()) {
            retwitUserId.add(user1.getId());
        }
        TwitDto twitDto = new TwitDto();
        twitDto.setId(twit.getId());
        twitDto.setContent(twit.getContent());
        twitDto.setCreatedAt(twit.getCreatedAt());
        twitDto.setImage(twit.getImage());
        twitDto.setTotalLikes(twit.getLikes().size());
        twitDto.setTotalReplies(twit.getReplyTweets().size());
        twitDto.setTotalRetweets(twit.getRetwitUsers().size());
        twitDto.setUser(user);
        twitDto.setLiked(isLiked);
        twitDto.setRetwit(isRetwited);
        twitDto.setRetwitUsersId(retwitUserId);
        twitDto.setReplyTwits(toTwitDtos(twit.getReplyTweets(), requestUser));
        twitDto.setVideo(twit.getVideo());

        return twitDto;
    }

    public static List<TwitDto> toTwitDtos(List<Twit> twits, User requestUser) {
        List<TwitDto> twitDtos = new ArrayList<>();
        for (Twit twit : twits) {
            TwitDto twitDto = toReplyTwitDto(twit, requestUser);
            twitDtos.add(twitDto);
        }
        return twitDtos;
    }

    private static TwitDto toReplyTwitDto(Twit twit, User requestUser) {
        UserDto user = UserDtoMapper.toUserDto(twit.getUser());
        boolean isLiked = TwitUtil.isLikedByRequestUser(requestUser, twit);
        boolean isRetwited = TwitUtil.isRetwitedByRequestUser(requestUser, twit);

        List<Long> retwitUserId = new ArrayList<>();
        for (User user1 : twit.getRetwitUsers()) {
            retwitUserId.add(user1.getId());
        }
        TwitDto twitDto = new TwitDto();
        twitDto.setId(twit.getId());
        twitDto.setContent(twit.getContent());
        twitDto.setCreatedAt(twit.getCreatedAt());
        twitDto.setImage(twit.getImage());
        twitDto.setTotalLikes(twit.getLikes().size());
        twitDto.setTotalReplies(twit.getReplyTweets().size());
        twitDto.setTotalRetweets(twit.getRetwitUsers().size());
        twitDto.setUser(user);
        twitDto.setLiked(isLiked);
        twitDto.setRetwit(isRetwited);
        twitDto.setRetwitUsersId(retwitUserId);
        twitDto.setVideo(twit.getVideo());

        return twitDto;
    }

}
