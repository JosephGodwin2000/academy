package com.hcl.academy.utits;

import org.springframework.stereotype.Component;

@Component
public class AppUtils {
    public static String generateUniqueId(String lastSequence) {
        long count = 0;
        String[] part = lastSequence.split("(?<=\\D)(?=\\d)");
        count = Long.parseLong(part[1]);

        return part[0] + ("00000" + (count + 1)).substring(String.valueOf(count).length());
    }
}
