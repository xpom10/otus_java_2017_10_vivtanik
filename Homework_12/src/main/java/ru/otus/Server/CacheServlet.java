package ru.otus.Server;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CacheServlet extends HttpServlet {

    private static final String CACHE_PAGE_SERVLET = "cache.html";

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) {
        Map<String,String> pageVariables = new HashMap<>();

    }

}
