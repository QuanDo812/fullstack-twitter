package com.twitter.demo.repository;

import com.twitter.demo.model.Twit;
import com.twitter.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TwitRepository extends JpaRepository<Twit, Long> {

    List<Twit> findAllByIsTwitTrueOrderByCreatedAtDesc();

    List<Twit> findByRetwitUsersContainsOrUser_idAndIsTwitTrueOrderByCreatedAtDesc(User user, Long userId);

    List<Twit> findByLikesContainingOrderByCreatedAtDesc(User user);

    @Query("SELECT t FROM Twit t JOIN t.likes l WHERE l.user.id=:userId")
    List<Twit> findByLikesUser_id(Long userId);

}
