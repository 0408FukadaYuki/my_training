package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Favorite;
import com.example.demo.model.FavoritePK;
import com.example.demo.model.User;

import java.util.List;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, FavoritePK> {
    List<Favorite> findByUser(User user);
}
