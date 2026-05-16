package com.Harry.Tasks.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.Harry.Tasks.domain.entities.TaskList;

public interface TaskListService {
	List<TaskList> listTaskLists();
	TaskList createTaskList(TaskList taskList);
	Optional<TaskList> getTaskList(UUID Id);
	TaskList updateTaskList(UUID taskListId, TaskList taskList);
	void deleteTaskList(UUID taskListId);
}
