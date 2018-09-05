package br.com.task.endpoint;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.task.model.Task;
import br.com.task.service.TaskService;

// Indico que é uma api restful
@RestController

// Neste ponto v1 é a versão da api e será usado dessa forma http://localhost:8080/v1
@RequestMapping("v1")
public class TaskEndpoint {
	
	private TaskService taskService;
	
	// Faço a injeção de dependencia, neste caso do meu servie onde 
	// contem as regras da aplicação 
	@Autowired
	public TaskEndpoint(TaskService taskService) {
		this.taskService = taskService;
	}
	
	// Estou usando aqui o CrossOrigin para não gerar erro de origem cruzada, 
	// mas em aplicações mais elaborada é orientado que se faça uma classe separada
	// para tratar esses cabeçalhos
	@CrossOrigin(origins = "http://localhost:3000")

	// Metodo para lista tasks, note que findAll é a do meu service que contem a camada de serviços (regras da aplicação)
	@GetMapping(value = "tasks")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
    }
	

	@CrossOrigin(origins = "http://localhost:3000")

	// Metodo que me retorna apenas 1 registro
	@GetMapping(value = "task/{id}")
    public ResponseEntity<?> findOne(@PathVariable Integer id) {
        return new ResponseEntity<>(taskService.findOne(id), HttpStatus.OK);
    }
	
	@CrossOrigin(origins = "http://localhost:3000")

	// Metodo que salva os dados da task e retorna o próprio objeto.
	@PostMapping(value = "task")
	public ResponseEntity<?> insert(@Valid @RequestBody Task obj) {
		Task task = taskService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.noContent().build();
	}

	@CrossOrigin(origins = "http://localhost:3000")

	// Atualiza dados
	@PutMapping(value = "task/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Task obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = taskService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@CrossOrigin(origins = "http://localhost:3000")

	// Deleta dados
	@DeleteMapping(value = "task/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		taskService.delete(id);
		return ResponseEntity.noContent().build();
	}


}