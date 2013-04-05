package com.forum.authentication;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

public class Encryption {

    private static Logger logger = Logger.getLogger(Encryption.class.getName());

    public String encryptUsingMd5(String input) {
        String encryptedPassword;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            String salt = "~!@#$%^&*()_=ASDFGHJKkl;'jkl;4123095678";
            messageDigest.update((input+salt).getBytes(), 0, input.length());
            encryptedPassword = new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            logger.severe("Failed to create md5 of input due to NoSuchAlgorithmException with message: " + e.getMessage());
            throw new UnsupportedOperationException("Failed to create md5 of input due to NoSuchAlgorithmException", e);
        }
        return encryptedPassword;
    }
}



