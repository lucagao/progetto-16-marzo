package com.example.demo.service;

import com.example.demo.model.ToDo;

import java.util.List;

public interface ToDoService {
    List<ToDo> getAllTodo();
    ToDo save(ToDo todo);
    ToDo update(ToDo todo);
    ToDo findByID(Long id);
    void delete(Long id);
}