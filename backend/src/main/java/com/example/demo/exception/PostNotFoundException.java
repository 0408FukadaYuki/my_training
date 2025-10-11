package com.example.demo.exception;

/**
 * 投稿取得の際、DBアクセスエラー発生時に使用するカスタムクラス
 */
public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String message) {
        super(message);
    }
}
