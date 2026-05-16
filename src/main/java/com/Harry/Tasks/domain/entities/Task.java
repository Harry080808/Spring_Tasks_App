package com.Harry.Tasks.domain.entities;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id",updatable = false,nullable = false)
	private UUID id;
	
	@Column(name = "title",nullable = false,updatable = true)
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "due_date")
	private LocalDateTime dueDate;
	
	@Column(name = "status",nullable = false)
	private TaskStatus status;
	
	@Column(name = "priority",nullable = false)
	private TaskPriority priority;
	
	@Column(name = "created",nullable = false,updatable = false)
	private LocalDateTime created;
	
	@Column(name = "updated",nullable = false)
	private LocalDateTime updated;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_list_id")
	private TaskList taskList;

	public Task() {
		super();
	}

	public Task(UUID id, String title, String description, LocalDateTime dueDate, TaskStatus status,
			TaskPriority priority, LocalDateTime created, LocalDateTime updated, TaskList taskList) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.status = status;
		this.priority = priority;
		this.created = created;
		this.updated = updated;
		this.taskList = taskList;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public TaskPriority getPriority() {
		return priority;
	}

	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	public TaskList getTaskList() {
		return taskList;
	}

	public void setTaskList(TaskList taskList) {
		this.taskList = taskList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(created, description, dueDate, id, priority, status, taskList, title, updated);
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
		return Objects.equals(created, other.created) && Objects.equals(description, other.description)
				&& Objects.equals(dueDate, other.dueDate) && Objects.equals(id, other.id) && priority == other.priority
				&& status == other.status && Objects.equals(taskList, other.taskList)
				&& Objects.equals(title, other.title) && Objects.equals(updated, other.updated);
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", dueDate=" + dueDate
				+ ", status=" + status + ", priority=" + priority + ", created=" + created + ", updated=" + updated
				+ ", taskList=" + taskList + "]";
	}
	
}
