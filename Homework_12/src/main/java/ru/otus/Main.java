package ru.otus;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import ru.otus.Cache.CacheEngineImpl;
import ru.otus.DataBase.DBServiceHibernateImpl;
import ru.otus.Server.AdminServlet;
import ru.otus.Server.CacheServlet;
import ru.otus.Server.SignInServlet;
import ru.otus.UserData.UserDataSet;

public class Main {

    private final static int PORT = 8090;
    private final static String PUBLIC_HTML = "public_html";
    private static DBServiceHibernateImpl dbService;



    public static void main(String[] args) throws Exception {
        dbService =  new DBServiceHibernateImpl(new CacheEngineImpl<>(5,5,5,true));
        dbService.save(new UserDataSet("login","123"));

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(AdminServlet.class, "/admin");
        context.addServlet(CacheServlet.class, "/cache");
        context.addServlet(SignInServlet.class, "/signIn");

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();
    }

    public static DBServiceHibernateImpl getDB() {
        return dbService;
    }
}
