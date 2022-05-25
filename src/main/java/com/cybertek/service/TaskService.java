package com.cybertek.service;

import com.cybertek.dto.TaskDTO;
import com.cybertek.entity.Task;

import java.util.List;

public interface TaskService {

    TaskDTO findById(Long id);

    List<TaskDTO> listAllTasks();

    Task save(TaskDTO taskDTO);

    void update(TaskDTO taskDTO);

    void delete(Long id);

}
