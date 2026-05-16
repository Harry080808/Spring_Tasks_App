package com.Harry.Tasks.Services.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Harry.Tasks.Services.TaskListService;
import com.Harry.Tasks.domain.entities.TaskList;
import com.Harry.Tasks.repositories.TaskListRepository;

@Service
public class TaskListServiceImpl implements TaskListService {
	
	private final TaskListRepository tasklistRepository;
	
	public TaskListServiceImpl(TaskListRepository tasklistRepository) {
		this.tasklistRepository = tasklistRepository;
	}

	@Override
	public List<TaskList> listTaskLists() {
		
		return tasklistRepository.findAll();
	}

	@Override
	public TaskList createTaskList(TaskList taskList) {
		if(null != taskList.getId()) {
			throw new IllegalArgumentException("Task List already has an ID!");
		}
		if(null == taskList.getTitle() || taskList.getTitle().isBlank()) {
			throw new IllegalArgumentException("Task List title must be present!");
		}
		LocalDateTime now  = LocalDateTime.now();
		return tasklistRepository.save(new TaskList(
				null,
				taskList.getTitle(),
				taskList.getDescription(),
				null,
				now,
				now
				));
	}

	@Override
	public Optional<TaskList> getTaskList(UUID Id) {
		
		return tasklistRepository.findById(Id);
	}

	@Transactional
	@Override
	public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
		if(null == taskList.getId()) {
			throw new IllegalArgumentException("tasklist must have an ID!");
		}
		
		if(!Objects.equals(taskList.getId(), taskListId)) {
			throw new IllegalArgumentException("Attempting to change task list ID, This is not permitted!");
		}
		
		TaskList existingTaskList = tasklistRepository.findById(taskListId).orElseThrow(() -> new IllegalArgumentException("Task list not found!"));
		
		existingTaskList.setTitle(taskList.getTitle());
		existingTaskList.setDescription(taskList.getDescription());
		existingTaskList.setUpdated(LocalDateTime.now());
		
		return tasklistRepository.save(existingTaskList);
	}

	@Override
	public void deleteTaskList(UUID taskListId) {
		tasklistRepository.deleteById(taskListId);
	}

}
