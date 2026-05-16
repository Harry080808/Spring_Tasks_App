package com.Harry.Tasks.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Harry.Tasks.Mappers.TaskListMapper;
import com.Harry.Tasks.Services.TaskListService;
import com.Harry.Tasks.domain.dto.TaskListDto;
import com.Harry.Tasks.domain.entities.TaskList;

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {
	
	private final TaskListService listService;
	private final TaskListMapper listMapper;
	
	public TaskListController(TaskListService listService, TaskListMapper listMapper) {
		this.listMapper = listMapper;
		this.listService = listService;
	}
	
	@GetMapping
	public List<TaskListDto> listTaskLists(){
		return listService.listTaskLists()
				.stream()
				.map(listMapper::toDto)
				.toList();
	}
	
	@PostMapping
	public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
		TaskList createdTaskList = listService.createTaskList(listMapper.fromDto(taskListDto));
		
		return listMapper.toDto(createdTaskList);
	}
	
	@GetMapping(path = "/{task_list_id}")
	public Optional<TaskListDto> getTaskList(@PathVariable("task_list_id") UUID taskListId){
		return listService.getTaskList(taskListId).map(listMapper::toDto);
	}
	
	@PutMapping(path = "/{task_list_id}")
	public TaskListDto updateTaskList(
			@PathVariable("task_list_id") UUID taskListId,
			@RequestBody TaskListDto taskListDto
			) {
		TaskList updatedTaskList = listService.updateTaskList(taskListId, listMapper.fromDto(taskListDto));
		
		return listMapper.toDto(updatedTaskList);
	}
	
	@DeleteMapping(path = "/{task_list_id}")
	public void deleteTaskList(@PathVariable("task_list_id") UUID taskListId) {
		listService.deleteTaskList(taskListId);
	}
}
