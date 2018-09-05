package br.com.task.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.task.model.Task;
import br.com.task.repository.TaskRepository;
import br.com.task.service.exceptions.DataIntegrityException;
import br.com.task.service.exceptions.ObjectNotFoundException;

// Aqui ficam as regras e camada de acesso a dados o que torna muito mais organizado o projeto
@Service
public class TaskService {
	
	@Autowired
	private TaskRepository repository;
	
    // Lista todas as tasks
    public List<Task> findAll() {
        List<Task> tasks = repository.findAll();
        if(tasks == null)
            throw new ObjectNotFoundException("Tasks não encontradas: " + Task.class.getName());
        return tasks;
    }
    
    // Lista apenas uma task
    public Task findOne(Integer id) {
        Optional<Task> task = repository.findById(id);
        return task.orElseThrow(() -> new ObjectNotFoundException(
                "Task não encontrada: " + id + ", tipo: " + Task.class.getName()));
    }
    
    // Insersão de dados com adicional de data e hora atual do java 8
    public Task insert(Task obj) {
    	LocalDate dataAtual = LocalDate.now();
        obj.setId(null);
        obj.setCreated(dataAtual);
        return repository.save(obj);
    }
        
    // Atualiza e salva a data da atualização    
    public Task update(Task obj) {
        Task newObj = findOne(obj.getId());
        updateData(newObj, obj);
        return repository.save(newObj);
    }
    
    // Regra para salvar dados sem sobrescrever alguns dados deixando no estado original.
    private void updateData(Task newObj, Task obj) {
    	LocalDate dataUpdate = LocalDate.now();
        newObj.setTitle(obj.getTitle());
        newObj.setDescription(obj.getDescription());
        newObj.setStatus(obj.getStatus());
        newObj.setUpdated(dataUpdate);
    }
    
    // Deleta dados
    public void delete(Integer id) {
        findOne(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível deletar task");
        }
    }
}
