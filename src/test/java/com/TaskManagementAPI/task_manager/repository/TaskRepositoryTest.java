package com.TaskManagementAPI.task_manager.repository;

import com.TaskManagementAPI.task_manager.entity.Task;
import com.TaskManagementAPI.task_manager.enums.Priority;
import com.TaskManagementAPI.task_manager.enums.TaskStatus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void saveAndFindTask() {
        Task task = new Task("Test Task", "Description", TaskStatus.PENDING, Priority.HIGH, LocalDate.now());
        Task savedTask = taskRepository.save(task);

        Optional<Task> foundTask = taskRepository.findById(savedTask.getId());
        assertTrue(foundTask.isPresent());
        assertEquals("Test Task", foundTask.get().getTitle());
    }

    @Test
    void deleteTask() {
        Task task = new Task("Test Task", "Description", TaskStatus.PENDING, Priority.HIGH, LocalDate.now());
        Task savedTask = taskRepository.save(task);

        taskRepository.deleteById(savedTask.getId());
        Optional<Task> deletedTask = taskRepository.findById(savedTask.getId());
        assertFalse(deletedTask.isPresent());
    }
}