package com.cybertek.service;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.TaskDTO;
import com.cybertek.entity.Task;

import java.util.List;

public interface TaskService {

    TaskDTO findById(Long id);

    List<TaskDTO> listAllTasks();

    Task save(TaskDTO taskDTO);

    void update(TaskDTO taskDTO);

    void delete(Long id);

    Integer totalCompletedTasks(String projectCode);

    Integer totalNonCompletedTasks(String projectCode);

    void deleteByProject(ProjectDTO projectDTO);

    List<TaskDTO> listAllTasksByProject(ProjectDTO projectDTO);

}
