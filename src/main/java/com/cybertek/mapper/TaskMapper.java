package com.cybertek.mapper;

import com.cybertek.dto.TaskDTO;
import com.cybertek.entity.Task;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Task convertToEntity(TaskDTO dto) {
        return modelMapper.map(dto, Task.class);
    }

    public TaskDTO convertToDTO(Task entity) {
        return modelMapper.map(entity, TaskDTO.class);
    }

}
