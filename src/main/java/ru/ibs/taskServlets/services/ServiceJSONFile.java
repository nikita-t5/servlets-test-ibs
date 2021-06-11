package ru.ibs.taskServlets.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.ibs.taskServlets.model.Task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ServiceJSONFile {

    private JSONObject createdList;
    private JSONArray arrayTasks;

    public void createEmptyJSONFile() {
        createdList = new JSONObject();
        arrayTasks = new JSONArray();
        writingToJSONFile();
    }

    public void addTasksToJSONFile(List<Task> tasks) {
        arrayTasks.clear();
        for (Task task : tasks) {
            JSONObject taskJSONObject = new JSONObject();
            taskJSONObject.put("taskName", task.getTaskName());
            taskJSONObject.put("deadline", task.getDeadline());
            arrayTasks.add(taskJSONObject);
        }
        writingToJSONFile();
    }

    private void writingToJSONFile() {
        createdList.put("To-Do List", arrayTasks);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/taskRepository.json"))) {
            bw.write(createdList.toString());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public JSONObject getCreatedList() {
        return createdList;
    }
}