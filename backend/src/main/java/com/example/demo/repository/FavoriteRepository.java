package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Favorite;
import com.example.demo.model.FavoritePK;
import com.example.demo.model.Post;
import com.example.demo.model.User;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, FavoritePK> {
    List<Favorite> findByUserOrderByCreatedAtDesc(User user);
    void deleteByPost(Post post);
}
