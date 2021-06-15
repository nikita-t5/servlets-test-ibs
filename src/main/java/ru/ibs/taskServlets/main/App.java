package ru.ibs.taskServlets.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import ru.ibs.taskServlets.servlets.FirstServlet;
import ru.ibs.taskServlets.servlets.SecondServlet;

public class App {
    public static void main(String[] args) {
        System.out.println("Start main class");
        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.addServlet(FirstServlet.class, "/f");
        context.addServlet(SecondServlet.class, "/s");
        server.setHandler(context);
        try {
            server.start();
            System.out.println("Server started");
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}