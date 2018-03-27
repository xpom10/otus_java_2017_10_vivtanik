package ru.otus.Server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.otus.DataBase.DBServiceHibernateImpl;
import ru.otus.MessageSystem.Address;
import ru.otus.MessageSystem.Addressee;
import ru.otus.MessageSystem.MessageSystem;
import ru.otus.MessageSystem.MessageSystemContext;
import ru.otus.MessageSystem.Messages.LoadUser;
import ru.otus.UserData.UserDataSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AdminServlet extends HttpServlet implements Addressee {

    private Address address;


    private static final String ADMIN_PAGE_SERVLET = "admin.html";
    public static final String LOGIN_PARAMETER_NAME = "login";
    public static final String PASS_PARAMETER_NAME = "password";


    @Autowired
    private MessageSystemContext messageSystemContext;

    private Map<String, Object> pageVariables;
    private UserDataSet user;


    public AdminServlet() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        messageSystemContext.setAdminAddress(new Address("adminAddress"));
        this.address = messageSystemContext.getAdminAddress();
        messageSystemContext.getMessageSystem().addAddressee(this);

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
            messageSystemContext.getMessageSystem().sendMessage(
                    new LoadUser(
                            address,
                            messageSystemContext.getDbAddress(),
                            requestUser)
                    );

            UserDataSet logInUser = getUser();

            if (logInUser != null && logInUser.equals(new UserDataSet(requestUser,requestPassword))) {
                request.getSession().setAttribute(LOGIN_PARAMETER_NAME, requestUser);
                response.sendRedirect("/cache");
            } else {
                pageVariables.put("message", "Incorrect login and password, repeat please");
                getPage(response);
            }
        } else {
            if (request.getSession().getAttribute(AdminServlet.LOGIN_PARAMETER_NAME) != null) {
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

    public void addUser(UserDataSet user) {
        System.out.println("user add " + user);
        this.user = user;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public MessageSystem getMessageSystem() {
        return messageSystemContext.getMessageSystem();
    }

    public UserDataSet getUser() {
        System.out.println("user get " + user);
        return user;
    }
}
