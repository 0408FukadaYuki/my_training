package com.example.demo.model.request;

import lombok.Data;
/**
 * お気に入り登録・削除APIに使用するリクエスト親クラス
 */
@Data
public class FavoriteBaseRequest {
    private String uuid;
    private Long postId;
}
