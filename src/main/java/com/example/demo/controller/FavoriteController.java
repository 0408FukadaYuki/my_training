package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.request.CreateFavoriteRequest;
import com.example.demo.service.FavoriteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    
    @Autowired
    private FavoriteService favoriteService;

    /**
     * お気に入り登録API
     * @param createFavoriteRequest　お気に入り登録に必要なユーザーのuuidと投稿のidを受け取る
     */
    @PostMapping("/create")
    public void createFavorite(@RequestBody CreateFavoriteRequest createFavoriteRequest) {
        favoriteService.createFavorite(createFavoriteRequest);
    }
    
}
