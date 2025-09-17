package com.example.demo.service;

import com.example.demo.model.request.CreateFavoriteRequest;

public interface FavoriteService {

    /**
     * お気に入り登録関数
     *
     * @param createFavoriteRequest リクエストボディで受け取るお気に入り登録するための情報
     */
    public void createFavorite(CreateFavoriteRequest createFavoriteRequest);
}
