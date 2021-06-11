package ru.ibs.taskServlets.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class App {
    public static void main(String[] args) {
        System.out.println("Start main class");
        Server server = new Server(8080);
        final String webAppPath = App.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        WebAppContext webapp = new WebAppContext(webAppPath, "/");
        server.setHandler(webapp);
        try {
            server.start();
            System.out.println("Server started");
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}