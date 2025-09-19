package com.example.demo.service;

import java.util.List;

import com.example.demo.model.request.CreateFavoriteRequest;
import com.example.demo.model.response.UserFavoriteResponse;

public interface FavoriteService {

    /**
     * お気に入り登録関数
     *
     * @param createFavoriteRequest リクエストボディで受け取るお気に入り登録するための情報
     */
    public void createFavorite(CreateFavoriteRequest createFavoriteRequest);

    /**
     * お気に入り取得関数
     *
     * @param uuid お気に入りを取得するためのユーザーのuuid
     */
    public  List<UserFavoriteResponse> getFavorite(String uuid);
}
