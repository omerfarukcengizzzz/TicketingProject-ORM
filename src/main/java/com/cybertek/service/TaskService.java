package com.cybertek.service;

import com.cybertek.dto.TaskDTO;
import com.cybertek.dto.UserDTO;

import java.util.List;

public interface TaskService extends CrudService<TaskDTO, Long>{
    List<TaskDTO> findTaskByManager(UserDTO manager);
}
