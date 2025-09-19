package com.example.demo.exception;

/**
 * お気に入り取得の際、DBアクセスエラー発生時に使用するカスタムクラス
 */
public class FavoriteNotGetException extends RuntimeException {
    public FavoriteNotGetException(String message) {
        super(message);
    }
}
