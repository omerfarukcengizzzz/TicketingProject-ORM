package com.cybertek.service;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.TaskDTO;
import com.cybertek.dto.UserDTO;
import com.cybertek.entity.Task;
import com.cybertek.enums.Status;

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

    List<TaskDTO> listAllTasksByStatusIsNot(Status status);

    List<TaskDTO> listAllTasksByProjectManager();

    void updateStatus(TaskDTO taskDTO);

    List<Task> listAllByAssignedEmployee(UserDTO employee);

    List<TaskDTO> listAllByStatusIsCompleted();

}
