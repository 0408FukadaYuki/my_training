package com.example.demo.exception;

/**
 * お気に入り削除の際、DBアクセスエラー発生時に使用するカスタムクラス
 */
public class FavoriteNotDeletedException extends RuntimeException {
    public FavoriteNotDeletedException(String message) {
        super(message);
    }
}
