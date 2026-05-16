package com.Harry.Tasks.Services.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Harry.Tasks.Services.TaskService;
import com.Harry.Tasks.domain.entities.Task;
import com.Harry.Tasks.domain.entities.TaskList;
import com.Harry.Tasks.domain.entities.TaskPriority;
import com.Harry.Tasks.domain.entities.TaskStatus;
import com.Harry.Tasks.repositories.TaskListRepository;
import com.Harry.Tasks.repositories.TaskRepository;

@Service
public class TaskServcieImpl implements TaskService{

	private final TaskRepository taskRepository;
	private final TaskListRepository taskListRepository;
	
	public TaskServcieImpl(TaskRepository taskRepository,TaskListRepository taskListRepository) {
		this.taskRepository = taskRepository;
		this.taskListRepository = taskListRepository;
	}
	@Override
	public List<Task> listTasks(UUID taskListId) {
		
		return taskRepository.findByTaskListId(taskListId);
	}
	
	@Transactional
	@Override
	public Task createTask(UUID taskListId, Task task) {
		if(null != task.getId()) {
			throw new IllegalArgumentException("Task already has an ID!");
		}
		if(null == task.getTitle() || task.getTitle().isBlank()) {
			throw new IllegalArgumentException("Task must have a title");
		}
		
		TaskStatus taskStatus = TaskStatus.OPEN;
		
		TaskPriority taskPriority = Optional.ofNullable(task.getPriority())
				.orElse(TaskPriority.MEDIUM);
		
		TaskList taskList = taskListRepository.findById(taskListId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Task List ID provided!"));
		
		LocalDateTime now = LocalDateTime.now();
		
		Task taskToSave = new Task(
				null,
				task.getTitle(),
				task.getDescription(),
				task.getDueDate(),
				taskStatus,
				taskPriority,
				now,
				now,
				taskList
				);
		
		return taskRepository.save(taskToSave);
	}
	
	@Override
	public Optional<Task> getTask(UUID taskListId, UUID taskId) {
		return taskRepository.findByTaskListIdAndId(taskListId, taskId);
	}
	
	@Transactional
	@Override
	public Task updateTask(UUID taskListId, UUID taskId, Task task) {
		if(null == task.getId()) {
			throw new IllegalArgumentException("Task must have an ID!");
		}
		if(null == task.getPriority()) {
			throw new IllegalArgumentException("Task must have a valid priority!");
		}
		if(null == task.getStatus()) {
			throw new IllegalArgumentException("Task must have a valid status");
		}
		
		Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
				.orElseThrow(() -> new IllegalArgumentException("Task not found!"));
		
		existingTask.setTitle(task.getTitle());
		existingTask.setDescription(task.getDescription());
		existingTask.setDueDate(task.getDueDate());
		existingTask.setPriority(task.getPriority());
		existingTask.setStatus(task.getStatus());
		existingTask.setUpdated(LocalDateTime.now());
		
		return taskRepository.save(existingTask);
	}
	
	@Transactional
	@Override
	public void deleteTask(UUID taskListId, UUID taskId) {
		taskRepository.deleteByTaskListIdAndId(taskListId, taskId);
	}

}
