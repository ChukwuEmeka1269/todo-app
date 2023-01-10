package com.rexcoin.springboottodoapp.controller;

import com.rexcoin.springboottodoapp.dto.TodoItemDto;
import com.rexcoin.springboottodoapp.model.Todo;
import com.rexcoin.springboottodoapp.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Objects;
@Slf4j
@Controller
public class TodoFormController {

    private final TodoRepository todoRepository;

    public TodoFormController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
    public String editTodoItem(@PathVariable Long id, Model model){
        var todoItem = todoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Todo item: "+ id + " not found"));
        model.addAttribute("todoItem", todoItem);
        return "update-item";
    }

    @RequestMapping(path = "/todo/{id}", method = RequestMethod.POST)
    public String displayUpdateForm(@PathVariable String id,
                                    @Valid Todo todo,
                                    BindingResult bindingResult,
                                    Model model){
        if(bindingResult.hasErrors()){
            todo.setId(Long.valueOf(id));
            return "update-item";
        }

        todo.setUpdateAt(LocalDateTime.now());
        todoRepository.save(todo);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public String deleteTodoItem(@PathVariable Long id,
                                 Model model){
        var todoItem = todoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Todo item: "+ id + " not found"));
        todoRepository.delete(todoItem);

        return "redirect:/";
    }
}
