package com.rexcoin.springboottodoapp.repository;

import com.rexcoin.springboottodoapp.model.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
}
