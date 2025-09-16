package com.example.demo.model.response;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 投稿取得APIで使用するレスポンスモデル
 */
@NoArgsConstructor
@Data
public class GetAllPostResponse {
    private String userId;
    private String userName;
    private String content;
    private Integer replyTo;
    private LocalDateTime createdAt;
}
