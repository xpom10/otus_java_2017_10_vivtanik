package ru.otus.Server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.otus.DataBase.DBService;
import ru.otus.DataBase.DBServiceHibernateImpl;
import ru.otus.UserData.UserDataSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class SignInServlet extends HttpServlet {
    private static final String SIGNIN_PAGE_SERVLET = "signIn.html";
    private static final String MESSAGE = "message";
    private Map<String, Object> pageVariables = new HashMap<>();

    @Autowired
    private DBService dbService;



    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        pageVariables.put(MESSAGE,"Please, enter login and password");
        getPage(response);
    }



    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {

        String requestUser = request.getParameter("login");
        String requestPassword = request.getParameter("password");
        String repeatRequestPassword = request.getParameter("repeatPassword");

        if ((requestUser != null && requestUser.length() != 0 && requestPassword.length() != 0) && requestPassword.equals(repeatRequestPassword) ) {
            dbService.save(new UserDataSet(requestUser,requestPassword));
            pageVariables.put(MESSAGE, "Login and password created");
            getPage(response);
        } else {
            pageVariables.put(MESSAGE, "Error!!! please, repeat");
            getPage(response);
        }
    }

    private void getPage(HttpServletResponse response) throws IOException {
        response.getWriter().println(Processor.instance().getPage(SIGNIN_PAGE_SERVLET, pageVariables));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
