package com.Harry.Tasks.Mappers;

import com.Harry.Tasks.domain.dto.TaskListDto;
import com.Harry.Tasks.domain.entities.TaskList;

public interface TaskListMapper {
	
	TaskList fromDto(TaskListDto tasklistdto);
	
	TaskListDto toDto(TaskList tasklist);
}
