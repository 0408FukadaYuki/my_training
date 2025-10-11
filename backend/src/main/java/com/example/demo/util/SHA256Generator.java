package com.example.demo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

/**
 * パスワードをSHA-256を使ってハッシュ化するクラス
 */

public class SHA256Generator {
    public static String generateSHA256(String password) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] sha256Byte = sha256.digest(password.getBytes());
            HexFormat hex = HexFormat.of().withLowerCase();
            String hexString = hex.formatHex(sha256Byte);
            return hexString;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("エラーが発生しました", e);
        }
    }
}
