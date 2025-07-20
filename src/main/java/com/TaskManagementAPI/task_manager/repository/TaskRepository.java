package com.TaskManagementAPI.task_manager.repository;

import com.TaskManagementAPI.task_manager.entity.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

}
