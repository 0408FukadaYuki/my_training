package com.example.demo.exception;

/**
 * 投稿削除の際、DBアクセスエラー発生時に使用するカスタムクラス
 */
public class PostNotDeletedException extends RuntimeException {
    public PostNotDeletedException(String message) {
        super(message);
    }
}
