package com.example.demo.exception;

/**
 * ログインの際、DBアクセスエラー発生時に使用するカスタムクラス
 */
public class NotLoginUserException extends RuntimeException {
    public NotLoginUserException(String message) {
        super(message);
    }
}
