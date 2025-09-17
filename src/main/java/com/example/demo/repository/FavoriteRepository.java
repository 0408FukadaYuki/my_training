package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Favorite;
import com.example.demo.model.FavoritePK;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, FavoritePK> {
}
