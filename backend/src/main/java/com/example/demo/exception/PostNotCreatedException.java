package com.example.demo.exception;

/**
 * 新規投稿の際、DBアクセスエラー発生時に使用するカスタムクラス
 */
public class PostNotCreatedException extends RuntimeException {
    public PostNotCreatedException(String message) {
        super(message);
    }
}
