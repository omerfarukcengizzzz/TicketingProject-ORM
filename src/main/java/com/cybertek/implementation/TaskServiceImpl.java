package com.cybertek.implementation;

import com.cybertek.dto.TaskDTO;
import com.cybertek.entity.Task;
import com.cybertek.enums.Status;
import com.cybertek.mapper.TaskMapper;
import com.cybertek.repository.TaskRepository;
import com.cybertek.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public TaskDTO findById(Long id) {
        return null;
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        List<Task> taskList = taskRepository.findAll();

        return taskList.stream()
                .map(taskMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Task save(TaskDTO taskDTO) {
        Task task = taskMapper.convertToEntity(taskDTO);
        task.setAssignedDate(LocalDate.now());
        task.setStatus(Status.OPEN);
        task.setId(taskDTO.getId());
        taskRepository.save(task);

        return task;
    }

    @Override
    public void update(TaskDTO taskDTO) {

    }

    @Override
    public void delete(Long id) {

    }
}
