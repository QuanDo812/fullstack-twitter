package com.twitter.demo.repository;

import com.twitter.demo.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query("SELECT l FROM Like l WHERE l.user.id=?1 AND l.twit.id=?2")
    public Like isLikeExist(Long userId, Long twitId);

    @Query("SELECT l FROM Like l WHERE l.twit.id=?1")
    public List<Like> findByTwitId(Long twitId);

}
