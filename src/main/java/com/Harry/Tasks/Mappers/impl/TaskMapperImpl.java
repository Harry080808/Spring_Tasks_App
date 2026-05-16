package com.Harry.Tasks.Mappers.impl;

import org.springframework.stereotype.Component;

import com.Harry.Tasks.Mappers.TaskMapper;
import com.Harry.Tasks.domain.dto.TaskDto;
import com.Harry.Tasks.domain.entities.Task;

@Component
public class TaskMapperImpl implements TaskMapper{

	@Override
	public Task fromDto(TaskDto taskdto) {
		// TODO Auto-generated method stub
		return new Task(
				taskdto.id(),
				taskdto.title(),
				taskdto.description(),
				taskdto.dueDate(),
				taskdto.status(),
				taskdto.priority(),
				null,
				null,
				null
				);
	}

	@Override
	public TaskDto toDto(Task task) {
		// TODO Auto-generated method stub
		return new TaskDto(
				task.getId(),
				task.getTitle(),
				task.getDescription(),
				task.getDueDate(),
				task.getPriority(),
				task.getStatus()
				);
	}

}
