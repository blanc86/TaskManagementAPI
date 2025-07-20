package com.TaskManagementAPI.task_manager.service;

import com.TaskManagementAPI.task_manager.dto.TaskRequestDto;
import com.TaskManagementAPI.task_manager.dto.TaskResponseDto;
import com.TaskManagementAPI.task_manager.entity.Task;
import com.TaskManagementAPI.task_manager.enums.TaskStatus;
import com.TaskManagementAPI.task_manager.enums.Priority;
import com.TaskManagementAPI.task_manager.exception.TaskNotFoundException;
import com.TaskManagementAPI.task_manager.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<TaskResponseDto> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public TaskResponseDto getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return convertToResponseDto(task);
    }

    public TaskResponseDto createTask(TaskRequestDto requestDto) {
        Task task = convertToEntity(requestDto);
        Task savedTask = taskRepository.save(task);
        return convertToResponseDto(savedTask);
    }

    public TaskResponseDto updateTask(Long id, TaskRequestDto requestDto) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        existingTask.setTitle(requestDto.getTitle());
        existingTask.setDescription(requestDto.getDescription());
        existingTask.setStatus(requestDto.getStatus() != null ? requestDto.getStatus() : TaskStatus.PENDING);
        existingTask.setPriority(requestDto.getPriority() != null ? requestDto.getPriority() : Priority.MEDIUM);
        existingTask.setDueDate(requestDto.getDueDate());

        Task savedTask = taskRepository.save(existingTask);
        return convertToResponseDto(savedTask);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }

    private TaskResponseDto convertToResponseDto(Task task) {
        return new TaskResponseDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate()
        );
    }

    private Task convertToEntity(TaskRequestDto dto) {
        return new Task(
                dto.getTitle(),
                dto.getDescription(),
                dto.getStatus() != null ? dto.getStatus() : TaskStatus.PENDING,
                dto.getPriority() != null ? dto.getPriority() : Priority.MEDIUM,
                dto.getDueDate()
        );
    }
}
