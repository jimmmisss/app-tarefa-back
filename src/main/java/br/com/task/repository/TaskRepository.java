package br.com.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.task.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}