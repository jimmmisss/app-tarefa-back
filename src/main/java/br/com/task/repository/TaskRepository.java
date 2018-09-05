package br.com.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.task.model.Task;

// Camada de acesso a dados, aqui não será colocado nada de regras,
// Isso fica cargo da camada service
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}