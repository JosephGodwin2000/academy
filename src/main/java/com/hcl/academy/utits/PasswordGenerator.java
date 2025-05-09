package com.hcl.academy.utits;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class PasswordGenerator {
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String DIGIT = "0123456789";
    private static final String SPECIAL_CHAR = "!@#$%^&*()?";

    private static final String PASSWORD_CHARS = CHAR_LOWER + CHAR_UPPER + DIGIT + SPECIAL_CHAR;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static String generateRandomPassword(int length) {
        if (length < 8) {
            throw new IllegalArgumentException("Password length must be at least 8 characters.");
        }

        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = SECURE_RANDOM.nextInt(PASSWORD_CHARS.length());
            password.append(PASSWORD_CHARS.charAt(randomIndex));
        }
        return password.toString();
    }
}
