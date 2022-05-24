package com.cybertek.implementation;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.entity.Project;
import com.cybertek.enums.Status;
import com.cybertek.mapper.ProjectMapper;
import com.cybertek.mapper.UserMapper;
import com.cybertek.repository.ProjectRepository;
import com.cybertek.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public ProjectDTO getByProjectCode(String code) {
        Project project = projectRepository.findByProjectCode(code);
        return projectMapper.convertToDTO(project);
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
        List<Project> projectList = projectRepository.findAll(Sort.by("projectCode"));
        return projectList.stream()
                .map(projectMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Project save(ProjectDTO dto) {
        dto.setStatus(Status.OPEN);
        Project project = projectMapper.convertToEntity(dto);
        return projectRepository.save(project);
    }

    @Override
    public void update(ProjectDTO dto) {
        // find the current dto on entity
        Project project = projectRepository.findByProjectCode(dto.getProjectCode());
        // convert the dto to entity
        Project convertedProject = projectMapper.convertToEntity(dto);
        // set id and status
        convertedProject.setId(project.getId());
        convertedProject.setStatus(project.getStatus());
        // save on db
        projectRepository.save(convertedProject);
    }

    @Override
    public void delete(String code) {
        // soft delete
        Project project = projectRepository.findByProjectCode(code);
        project.setIsDeleted(true);
        projectRepository.save(project);
    }

    @Override
    public void complete(String projectCode) {
        Project project = projectRepository.findByProjectCode(projectCode);
        project.setStatus(Status.COMPLETE);
        projectRepository.save(project);
    }
}
