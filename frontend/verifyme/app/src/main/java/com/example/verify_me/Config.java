package com.example.verify_me;

public class Config {
    public static String BASE_URL = "http://10.0.2.2:3000/api/";
    public static final String FALLBACK_URL = "http://192.168.192.105:5000/";
    
    public static String getBaseUrl() {
        return BASE_URL != null ? BASE_URL : FALLBACK_URL;
    }
    
    public static void setBaseUrl(String url) {
        BASE_URL = url;
    }
}