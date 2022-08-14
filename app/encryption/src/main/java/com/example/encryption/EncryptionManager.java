package com.example.encryption;

import android.util.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionManager {

    private static EncryptionManager instance = null;
    private final static String TOKEN_KEY = "fqJfdzGDvfwbedsKSUGty3VZ9taXxMVw";
    MessageDigest digest = null;
    String hash;

    private EncryptionManager() {
    }

    public static EncryptionManager getInstance() {
        if (instance == null) {
            instance = new EncryptionManager();
        }
        return instance;
    }

    public static String encrypt(String plain) {
        try {
            byte[] iv = new byte[16];
            new SecureRandom().nextBytes(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(TOKEN_KEY.getBytes(StandardCharsets.UTF_8),
                    "AES"), new IvParameterSpec(iv));
            byte[] cipherText = cipher.doFinal(plain.getBytes(StandardCharsets.UTF_8));
            byte[] ivAndCipherText = getCombinedArray(iv, cipherText);
            return Base64.encodeToString(ivAndCipherText, Base64.NO_WRAP);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String encoded) {
        try {
            byte[] ivAndCipherText = Base64.decode(encoded, Base64.NO_WRAP);
            byte[] iv = Arrays.copyOfRange(ivAndCipherText, 0, 16);
            byte[] cipherText = Arrays.copyOfRange(ivAndCipherText, 16, ivAndCipherText.length);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(TOKEN_KEY.getBytes(StandardCharsets.UTF_8),
                    "AES"), new IvParameterSpec(iv));
            return new String(cipher.doFinal(cipherText), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] getCombinedArray(byte[] one, byte[] two) {
        byte[] combined = new byte[one.length + two.length];
        for (int i = 0; i < combined.length; ++i) {
            combined[i] = i < one.length ? one[i] : two[i - one.length];
        }
        return combined;
    }

    private static String bytesToHexString(byte[] bytes) {
        // http://stackoverflow.com/questions/332079
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xFF & aByte);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public String getSHA256(String data) {
        try {
            digest = MessageDigest.getInstance("SHA-256");
            digest.update(TOKEN_KEY.getBytes());

            hash = bytesToHexString(digest.digest());
            return hash;
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return "";
    }

    String encodeBase64(String data) {
        try {
            return Base64.encodeToString(data.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    String decodeBase64(String data) {
        try {
            return new String(Base64.decode(data, Base64.DEFAULT), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}