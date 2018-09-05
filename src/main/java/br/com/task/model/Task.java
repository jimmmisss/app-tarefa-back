package br.com.task.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// Entidade que é refletida no bando de dados
@Entity
public class Task implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String title;
	private String description;
	private Boolean status;
	private LocalDate created;
	private LocalDate updated;
	private LocalDate removed;
	private LocalDate conclusion;
	
	public Task() {
	}

	public Task(Integer id, String title, String description, Boolean status, LocalDate created, LocalDate updated, LocalDate removed,
			LocalDate conclusion) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.created = created;
		this.updated = updated;
		this.removed = removed;
		this.conclusion = conclusion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public LocalDate getCreated() {
		return created;
	}

	public void setCreated(LocalDate created) {
		this.created = created;
	}

	public LocalDate getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDate updated) {
		this.updated = updated;
	}

	public LocalDate getRemoved() {
		return removed;
	}

	public void setRemoved(LocalDate removed) {
		this.removed = removed;
	}

	public LocalDate getConclusion() {
		return conclusion;
	}

	public void setConclusion(LocalDate conclusion) {
		this.conclusion = conclusion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	
	
}
