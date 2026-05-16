package com.Harry.Tasks.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Harry.Tasks.domain.entities.Task;

public interface TaskRepository extends JpaRepository<Task, UUID>{
	List<Task> findByTaskListId(UUID taskListId);
	Optional<Task> findByTaskListIdAndId(UUID taskListId,UUID Id);
	void deleteByTaskListIdAndId(UUID taskListId,UUID Id);
}
