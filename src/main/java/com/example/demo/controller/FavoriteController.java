package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.request.CreateFavoriteRequest;
import com.example.demo.model.response.UserFavoriteResponse;
import com.example.demo.service.FavoriteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    /**
     * お気に入り登録API
     * 
     * @param createFavoriteRequest お気に入り登録に必要なユーザーのuuidと投稿のidを受け取る
     */
    @PostMapping("/create")
    public void createFavorite(@RequestBody CreateFavoriteRequest createFavoriteRequest) {
        favoriteService.createFavorite(createFavoriteRequest);
    }

    /**
     * お気に入り取得API
     * 
     * @param uuid お気に入りを取得するためのユーザーのuuid
     * @return uuidに紐づくお気に入り登録した投稿のリスト
     */
    @GetMapping("/get/{uuid}")
    public List<UserFavoriteResponse> getFavorite(@PathVariable String uuid) {
        return favoriteService.getFavorite(uuid);
    }

}
