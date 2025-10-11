package com.example.demo.exception;

/**
 * お気に入り登録の際、DBアクセスエラー発生時に使用するカスタムクラス
 */
public class FavoriteNotCreatedException extends RuntimeException {
    public FavoriteNotCreatedException(String message) {
        super(message);
    }
}
