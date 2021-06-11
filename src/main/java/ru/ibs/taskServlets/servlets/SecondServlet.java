package ru.ibs.taskServlets.servlets;

import org.json.simple.JSONObject;
import ru.ibs.taskServlets.model.Task;
import ru.ibs.taskServlets.services.ServiceJSONFile;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/second")  //не работает
public class SecondServlet extends HttpServlet {

    List<Task> tasks;
    ServiceJSONFile serviceJSONFile;

    @Override
    public void init() {
        serviceJSONFile = new ServiceJSONFile();
        serviceJSONFile.createEmptyJSONFile();
        tasks = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        serviceJSONFile.addTasksToJSONFile(tasks);
        JSONObject json = serviceJSONFile.getCreatedList();
        PrintWriter writer = resp.getWriter();
        writer.println(json);
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF8");
        resp.setContentType("text/html");
        String taskName = req.getParameter("taskName");
        String deadline = req.getParameter("deadline");
        Task task = new Task(taskName, deadline);
        tasks.add(task);
        serviceJSONFile.addTasksToJSONFile(tasks);
        try (PrintWriter writer = resp.getWriter()) {
            String stringView = String.format("<h2>added new task: %s deadline: %s </h2>", taskName, deadline);
            writer.println(stringView);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF8");
        resp.setContentType("text/html");
        String taskNameForChange = req.getParameter("taskNameForChange");
        String correctedDeadline = req.getParameter("correctedDeadline");
        for (Task task : tasks) {
            if (task.getTaskName().equals(taskNameForChange))
                task.setDeadline(correctedDeadline);
        }
        serviceJSONFile.addTasksToJSONFile(tasks);
        try (PrintWriter writer = resp.getWriter()) {
            String stringView = String.format("<h2>changed task: %s deadline: %s </h2>", taskNameForChange, correctedDeadline);
            writer.println(stringView);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF8");
        resp.setContentType("text/html");
        String taskNameForDelete = req.getParameter("taskNameForDelete");
        tasks.removeIf(task -> task.getTaskName().equals(taskNameForDelete));
        serviceJSONFile.addTasksToJSONFile(tasks);
        try (PrintWriter writer = resp.getWriter()) {
            String stringView = String.format("<h2>deleted task: %s</h2>", taskNameForDelete);
            writer.println(stringView);
        }
    }
}