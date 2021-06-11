package ru.ibs.taskServlets.servlets;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/first")  //не работает
public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        JSONParser parser = new JSONParser();
        String jsonStr = String.format("{\"request\":[{\"method\": \"get\", \"time\": \"%s\"}]}", new Date());
        try {
            JSONObject json = (JSONObject) parser.parse(jsonStr);
            PrintWriter writer = resp.getWriter();
            writer.println(json);
            writer.close();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}