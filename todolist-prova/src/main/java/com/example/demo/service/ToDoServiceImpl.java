package com.example.demo.service;

import com.example.demo.model.ToDo;
import com.example.demo.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ToDoServiceImpl implements  ToDoService {

    @Autowired private ToDoRepository todoRepository;


    @Override
    public List<ToDo> getAllTodo() {

        return (List<ToDo>) todoRepository.findAll();
    }

    @Override
    public ToDo save(ToDo todo){
        todo.setCreatedDate(new Date());
        return  todoRepository.save(todo);
    }

    @Override
    public ToDo update(ToDo todo){
        todo.setUpdatedDate(new Date());
        return  todoRepository.save(todo);
    }
    @Override
    public void delete(Long id){
       todoRepository.deleteById(id);
    }

    @Override
    public ToDo findByID(Long id) {
        Optional<ToDo> employee = todoRepository.findById(id);
        if(employee.isPresent()) {
            return employee.get();
        } else {
            return null;
        }
    }


}