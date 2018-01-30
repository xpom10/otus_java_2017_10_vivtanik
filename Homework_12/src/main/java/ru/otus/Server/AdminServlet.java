package ru.otus.Server;

import ru.otus.DataBase.DBServiceHibernateImpl;
import ru.otus.Main;
import ru.otus.UserData.UserDataSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdminServlet extends HttpServlet {

    private static final String ADMIN_PAGE_SERVLET = "admin.html";
    public static final String LOGIN_PARAMETER_NAME = "login";
    public static final String PASS_PARAMETER_NAME = "password";

    private DBServiceHibernateImpl dbService;
    private Map<String, Object> pageVariables;


    public AdminServlet(DBServiceHibernateImpl dbService) {
        this.dbService = dbService;
    }


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        pageVariables = new HashMap<>();
        pageVariables.put("message", "Enter login and password");

        getPage(response);

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        pageVariables = new HashMap<>();

        String requestUser = request.getParameter(LOGIN_PARAMETER_NAME);
        String requestPassword = request.getParameter(PASS_PARAMETER_NAME);

        if (requestUser != null && requestPassword != null && !requestUser.equals("") && !requestPassword.equals("")) {
            UserDataSet logInUser = dbService.getByName(requestUser);
            if (logInUser != null && logInUser.equals(new UserDataSet(requestUser,requestPassword))) {
                request.getSession().setAttribute(LOGIN_PARAMETER_NAME, requestUser);
                response.sendRedirect("/cache");
            } else {
                pageVariables.put("message", "Incorrect login and password, repeat please");
                getPage(response);
            }
        }
    }

    private void getPage(HttpServletResponse response) throws IOException {
        response.getWriter().println(Processor.instance().getPage(ADMIN_PAGE_SERVLET, pageVariables));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
