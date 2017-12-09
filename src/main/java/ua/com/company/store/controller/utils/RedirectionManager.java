package ua.com.company.store.controller.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by Владислав on 08.12.2017.
 */
public class RedirectionManager {
    public static String REDIRECTION = "REDIRECTION";
    public static String MESSAGE_ENCODING = "UTF-8";

    public RedirectionManager() {
    }

    private static final class Holder {
        static final RedirectionManager INSTANSE = new RedirectionManager();
    }

    public static RedirectionManager getRediractionManger() {
        return Holder.INSTANSE;
    }

    public void redirectWithParams(ServletWrapper servletWrapper, String redirectionPath, Map<String, String> urlParams) throws UnsupportedEncodingException {
        String urlRedirWithParams = genareteUrlParh(servletWrapper.getRequest(), redirectionPath) + generateUrlParams(urlParams);
        redirect(servletWrapper, redirectionPath);
    }

    public void redirect(ServletWrapper servletWrapper, String path) {
        try {
            servletWrapper.getResponse().sendRedirect(genareteUrlParh(servletWrapper.getRequest(), path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String genareteUrlParh(HttpServletRequest request, String path) {
        return new StringBuffer(request.getContextPath()).append(request.getServletPath()).append(path).toString();
    }

    private String generateUrlParams(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuffer stringBuffer = new StringBuffer("?");
        for (String urlParamName : params.keySet()) {
            stringBuffer.append(urlParamName).append("=").append(URLEncoder.encode(params.get(urlParamName), MESSAGE_ENCODING)).append("&");
        }
        return stringBuffer.toString();
    }
}
