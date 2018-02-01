package ru.otus.Server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.otus.DataBase.DBService;
import ru.otus.DataBase.DBServiceHibernateImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CacheServlet extends HttpServlet {

    private static final String CACHE_PAGE_SERVLET = "cache.html";

    @Autowired
    private DBServiceHibernateImpl dbService;

    public CacheServlet() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        String login = (String) request.getSession().getAttribute(AdminServlet.LOGIN_PARAMETER_NAME);

        if (login != null) {
            Map<String, Object> pageVariables = new HashMap<>();
            pageVariables.put("login", login);
            pageVariables.putAll(dbService.getCache().getStat());
            response.getWriter().println(Processor.instance().getPage(CACHE_PAGE_SERVLET, pageVariables));
            setOK(response);
        } else {
            response.sendRedirect("/admin");
            setUnauthorized(response);
        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute(AdminServlet.LOGIN_PARAMETER_NAME);
        response.sendRedirect("/admin");
    }

    private void setOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void setUnauthorized(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

}
