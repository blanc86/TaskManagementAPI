package com.TaskManagementAPI.task_manager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.TaskManagementAPI.task_manager.dto.TaskRequestDto;
import com.TaskManagementAPI.task_manager.enums.Priority;
import com.TaskManagementAPI.task_manager.enums.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createTask_ValidData_Returns201() throws Exception {
        TaskRequestDto request = new TaskRequestDto();
        request.setTitle("Test Task");
        request.setDescription("Test Description");
        request.setStatus(TaskStatus.PENDING);
        request.setPriority(Priority.HIGH);
        request.setDueDate(LocalDate.now().plusDays(7));

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Task"));
    }

    @Test
    void createTask_InvalidData_Returns400() throws Exception {
        TaskRequestDto request = new TaskRequestDto();
        request.setTitle("Hi"); // Too short
        request.setDescription("");

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))).andExpect(status().isBadRequest());
    }

    @Test
    void getTask_NonExistent_Returns404() throws Exception {
        mockMvc.perform(get("/api/tasks/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllTasks_ReturnsEmptyList() throws Exception {
        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}