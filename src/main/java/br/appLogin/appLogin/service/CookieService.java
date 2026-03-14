package br.appLogin.appLogin.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Optional;

public class CookieService {
    public static void setCookie(HttpServletResponse response, String key, String value, int seconds) {
        try {
            Cookie cookie = new Cookie(key, URLEncoder.encode(value, "UTF-8"));
            cookie.setMaxAge(seconds);
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException ignored) {
        }
    }

    public static String getCookie(HttpServletRequest request, String key) {
        String value = Optional.ofNullable(request.getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(cookie -> key.equals(cookie.getName()))
                        .findAny())
                .map(Cookie::getValue)
                .orElse(null);
        if (value == null) {
            return null;
        }
        try {
            return URLDecoder.decode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
