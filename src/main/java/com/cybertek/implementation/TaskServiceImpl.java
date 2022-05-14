package com.cybertek.implementation;

import com.cybertek.dto.TaskDTO;
import com.cybertek.dto.UserDTO;
import com.cybertek.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl extends AbstractMapService<TaskDTO, Long> implements TaskService {
    @Override
    public TaskDTO save(TaskDTO obj) {
        return super.save(obj.getId(), obj);
    }

    @Override
    public TaskDTO findByID(Long id) {
        return super.findByID(id);
    }

    @Override
    public List<TaskDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(TaskDTO obj) {
        var newTask = findByID(obj.getId());
        obj.setAssignedDate(newTask.getAssignedDate());
        obj.setStatus(newTask.getStatus());

        super.update(obj.getId(), obj);
    }

    @Override
    public void deleteByID(Long id) {
        super.deleteByID(id);
    }

    @Override
    public void delete(TaskDTO obj) {
        super.delete(obj);
    }

    @Override
    public List<TaskDTO> findTaskByManager(UserDTO manager) {
        return super.findAll().stream()
                .filter(t -> t.getProject().getAssignedManager().equals(manager))
                .collect(Collectors.toList());
    }
}
