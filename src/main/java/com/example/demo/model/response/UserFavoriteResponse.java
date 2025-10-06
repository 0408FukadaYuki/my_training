package com.example.demo.model.response;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * お気に入り取得APIで使用するレスポンスモデル
 */
@Data
@NoArgsConstructor
public class UserFavoriteResponse {
    private String uuid;
    private Long postId;
    private String userId;
    private String userName;
    private String content;
    private Integer replyTo;
    private LocalDateTime createdAt;
    private LocalDateTime favoriteCreatedAt;
}
