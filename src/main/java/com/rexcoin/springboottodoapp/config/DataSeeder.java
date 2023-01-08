package com.rexcoin.springboottodoapp.config;

import com.rexcoin.springboottodoapp.model.Todo;
import com.rexcoin.springboottodoapp.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataSeeder implements CommandLineRunner {
    private final TodoRepository todoRepository;

    public DataSeeder(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadSeedData();
    }

    private void loadSeedData() {
        if(todoRepository.count() == 0){
            var todo1 = new Todo("Finish Spring5 course");
            todo1.setCreatedBy("ADMIN");
            var todo2 = new Todo("Watch Mosh course");
            todo2.setCreatedBy("ADMIN");

            todoRepository.save(todo1);
            todoRepository.save(todo2);
        }

        log.info("Number of Todo items: {}", todoRepository.count());
    }
}
