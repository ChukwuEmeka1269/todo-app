package com.rexcoin.springboottodoapp.controller;

import com.rexcoin.springboottodoapp.dto.TodoItemDto;
import com.rexcoin.springboottodoapp.model.Todo;
import com.rexcoin.springboottodoapp.repository.TodoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/")
    public String index(Model model){
        log.debug("request to GET index");
        Iterable<Todo> allTodoItems = todoRepository.findAll();
        var listOfAllTodoItems = StreamSupport.stream(allTodoItems.spliterator(), false).collect(Collectors.toList());
        model.addAttribute("todoItems", listOfAllTodoItems);
        model.addAttribute("today", LocalDateTime.now().getDayOfWeek());
        return "index";
    }

    @RequestMapping(value = "/create-todo", method = RequestMethod.GET)
    public String showCreateTodoItemForm( Model model){
        model.addAttribute("newTodo", new Todo());
        return "create-todo";
    }

    @RequestMapping(value = "/new-todo", method = RequestMethod.POST)
    public String createNewTodoItem(@Valid  Todo todo, BindingResult result){
        if(result.hasErrors())return "create-todo";
        todo.setCreatedAt(LocalDateTime.now());
        todo.setCreatedBy("Anonymous");
        todoRepository.save(todo);
        return "redirect:/";
    }


}
