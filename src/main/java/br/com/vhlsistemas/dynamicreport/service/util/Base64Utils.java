package br.com.vhlsistemas.dynamicreport.service.util;

import java.util.Base64;

public class Base64Utils {

    public static final String BASE64_START = "data:";
    public static final String BASE64_CONTENTTYPE_SEPARATOR_INIT = ":";
    public static final String BASE64_CONTENTTYPE_SEPARATOR_END = ";";
    public static final String BASE64_VALUE_SEPARATOR = ",";
    public static final String CONTENTTYPE_EXT_SEPARATOR = "/";

    public static String readContentType(String base64) {
        if (!isValidBase64(base64)) {
            throw new RuntimeException("Invalid Base 64");
        }
        return base64.substring(base64.indexOf(BASE64_CONTENTTYPE_SEPARATOR_INIT) + 1, base64.indexOf(BASE64_CONTENTTYPE_SEPARATOR_END));
    }

    public static String readExtension(String base64) {
        return ".".concat(readContentType(base64).split(CONTENTTYPE_EXT_SEPARATOR)[1]);
    }

    public static byte[] readValue(String base64) {
        if (!isValidBase64(base64)) {
            throw new RuntimeException("Invalid Base 64");
        }

        String file = base64.split(BASE64_VALUE_SEPARATOR)[1];
        return Base64.getDecoder().decode(file);
    }

    private static boolean isValidBase64(String base64) {
        return base64.startsWith(BASE64_START) && base64.contains(BASE64_VALUE_SEPARATOR) && base64.contains(BASE64_CONTENTTYPE_SEPARATOR_INIT) &&
                base64.contains(BASE64_CONTENTTYPE_SEPARATOR_END);
    }
}

