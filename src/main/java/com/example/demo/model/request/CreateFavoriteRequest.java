package com.example.demo.model.request;

import lombok.Data;
/**
 * お気に入り登録APIに使用するリクエストモデル
 */
@Data
public class CreateFavoriteRequest {
    private String uuid;
    private Long postId;
}
