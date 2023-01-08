package com.rexcoin.springboottodoapp.controller;

import com.rexcoin.springboottodoapp.model.Todo;
import com.rexcoin.springboottodoapp.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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
        return "index.html";
    }
}
