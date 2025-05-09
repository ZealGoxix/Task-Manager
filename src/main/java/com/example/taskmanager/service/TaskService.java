package com.example.taskmanager.service;

import org.springframework.stereotype.Service;

import com.example.taskmanager.model.Task;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private Long nextId = 1L;

    public Task addTask(String title) {
        Task task = new Task(nextId, title, false);
        tasks.add(task);
        nextId++;
        return task;
    }

    public void deleteTask(Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
        nextId--; // Decrement nextId to avoid gaps in IDs
        reassignIds(); // Reassign IDs after deletion
    }

    private void reassignIds() {
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).setId((long) (i + 1));
        }
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

}
