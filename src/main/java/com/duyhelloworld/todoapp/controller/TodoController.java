package com.duyhelloworld.todoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duyhelloworld.todoapp.model.Todo;
import com.duyhelloworld.todoapp.service.TodoService;

@RestController
@RequestMapping(value = "api/v1/todo")
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping
    public List<Todo> getTodoByLimit(
        @RequestParam(required = false) Integer limit) {
        return service.getTodos(limit);
    }

    @GetMapping("{id}")
    public Todo getTodoById(
        @PathVariable(required = false) Long id) {
        return service.getTodo(id);
    }

    @PostMapping
    public Todo addNewTodo(@RequestBody Todo todo) {
        return service.add(todo);
    }

    @PutMapping("{id}")
    public Todo updateTodo(
        @PathVariable(required = false) Long id,
        @RequestBody(required = false) Todo todo 
    ) {
        return service.updateTodo(id, todo);
    }

    @DeleteMapping("{id}")
    public void deleteTodo(@PathVariable Long id) {
        service.deleteTodo(id);
    }

    @DeleteMapping
    public void deleteTodos(@RequestBody List<Long> ids) {
        service.deleteTodo(ids);
    }
}