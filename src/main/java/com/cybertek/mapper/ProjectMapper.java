package com.cybertek.mapper;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.entity.Project;
import com.cybertek.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProjectRepository projectRepository;

    public Project convertToEntity(ProjectDTO projectDTO) {
        return modelMapper.map(projectDTO, Project.class);
    }

    public ProjectDTO convertToDTO(Project project) {
        return modelMapper.map(project, ProjectDTO.class);
    }

}
