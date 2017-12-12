package ua.com.company.store.controller.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Владислав on 06.12.2017.
 */
public class CommandKeyGenerator {
    private CommandKeyGenerator() {
    }
    public static String generateCommandKeyByRequest(HttpServletRequest request) {
        String method = request.getMethod().toUpperCase();
        String path = request.getRequestURI();

        return method + ":" + path;
    }
}