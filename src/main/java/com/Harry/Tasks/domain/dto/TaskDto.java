package com.Harry.Tasks.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.Harry.Tasks.domain.entities.TaskPriority;
import com.Harry.Tasks.domain.entities.TaskStatus;

public record TaskDto(
		UUID id,
		String title,
		String description,
		LocalDateTime dueDate,
		TaskPriority priority,
		TaskStatus status
		) {

}
