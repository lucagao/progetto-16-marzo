package com.example.demo.controller;

import com.example.demo.model.ToDo;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ToDoController {

    @Autowired private ToDoService todoService;

    @GetMapping("/")
    public String ToDo(Model model){
        List<ToDo>todos = todoService.getAllTodo();
        model.addAttribute("todos", todos);
        model.addAttribute("todo", new ToDo());
        model.addAttribute("title", "ToDo");
        model.addAttribute("isAdd", true);
        return "todo";

    }

    @GetMapping("/test")
    public String test(Model model){
        return "test";
    }

    @PostMapping(value="/save")
    public String save (@ModelAttribute ToDo todo, RedirectAttributes redirectAttributes, Model model) {
        ToDo dbtodo = todoService.save(todo);
        if(dbtodo != null) {
            redirectAttributes.addFlashAttribute("successmessage", "ToDo is saved successfully");
            return "redirect:/";
        }else {
            model.addAttribute("errormessage", "ToDo is not save, Please try again");
            model.addAttribute("todo", todo);
            return "todo";
        }
    }

    @GetMapping(value = "/getToDo/{id}")
    public String getTodo(@PathVariable Long id, Model model){
        ToDo todo = todoService.findByID(id);
        List<ToDo>todos = todoService.getAllTodo();
        model.addAttribute("todo", todo);
        model.addAttribute("todos", todos);
        model.addAttribute("title", "Edit ToDo");
        model.addAttribute("isAdd", false);
        return "todo";

    }

    @PostMapping(value="/update")
    public String update (@ModelAttribute ToDo todo, RedirectAttributes redirectAttributes, Model model) {
        ToDo dbtodo = todoService.update(todo);
        if(dbtodo != null) {
            redirectAttributes.addFlashAttribute("successmessage", "ToDo is updated successfully");
            return "redirect:/";
        }else {
            model.addAttribute("errormessage", "ToDo is not updated, Please try again");
            model.addAttribute("todo", todo);
            return "todo";
        }
    }
    @GetMapping(value ="/deleteToDo/{id}")
    public String deleteTodo(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        todoService.delete(id);
        redirectAttributes.addFlashAttribute("successmessage", "ToDo is Deleted successfully");

        return "redirect:/";
    }
}