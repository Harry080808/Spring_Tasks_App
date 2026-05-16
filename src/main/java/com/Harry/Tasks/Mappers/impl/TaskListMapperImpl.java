package com.Harry.Tasks.Mappers.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.Harry.Tasks.Mappers.TaskListMapper;
import com.Harry.Tasks.Mappers.TaskMapper;
import com.Harry.Tasks.domain.dto.TaskListDto;
import com.Harry.Tasks.domain.entities.Task;
import com.Harry.Tasks.domain.entities.TaskList;
import com.Harry.Tasks.domain.entities.TaskStatus;

@Component
public class TaskListMapperImpl implements TaskListMapper{
	
	private final TaskMapper taskMapper;
	
	public TaskListMapperImpl(TaskMapper taskMapper) {
		this.taskMapper = taskMapper;
	}

	@Override
	public TaskList fromDto(TaskListDto tasklistdto) {
		// TODO Auto-generated method stub
		return new TaskList(
				tasklistdto.id(),
				tasklistdto.title(),
				tasklistdto.description(),
				Optional.ofNullable(tasklistdto.tasks()).map(tasks -> tasks.stream()
						.map(taskMapper::fromDto)
						.toList()
						).orElse(null),
				null,
				null
				);
	}

	@Override
	public TaskListDto toDto(TaskList tasklist) {
		
		return new TaskListDto(
				tasklist.getId(),
				tasklist.getTitle(),
				tasklist.getDescription(),
				Optional.ofNullable(tasklist.getTasks()).map(List::size).orElse(0),
				calculateTaskListProgress(tasklist.getTasks()),
				Optional.ofNullable(tasklist.getTasks())
					.map(tasks -> 
					tasks.stream().map(taskMapper::toDto).toList()
							).orElse(null)
				);
	}
	
	private Double calculateTaskListProgress(List<Task> tasks) {
		if(tasks == null) {
			return null;
		}
		
		long closedTaskCount = tasks.stream()
				.filter(task -> TaskStatus.CLOSED == task.getStatus())
				.count();
		
		return (double)closedTaskCount / tasks.size();
	}
}
