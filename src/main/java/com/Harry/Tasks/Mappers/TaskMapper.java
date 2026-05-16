package com.Harry.Tasks.Mappers;

import com.Harry.Tasks.domain.dto.TaskDto;
import com.Harry.Tasks.domain.entities.Task;

public interface TaskMapper {
	
	Task fromDto(TaskDto taskdto);
	
	TaskDto toDto(Task task);
}
