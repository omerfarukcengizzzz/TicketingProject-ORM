package com.cybertek.implementation;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.enums.Status;
import com.cybertek.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO, String> implements ProjectService {
    @Override
    public ProjectDTO save(ProjectDTO obj) {
        return super.save(obj.getProjectCode(), obj);
    }

    @Override
    public ProjectDTO findByID(String s) {
        return super.findByID(s);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(ProjectDTO obj) {
        super.delete(obj);
    }

    @Override
    public void deleteByID(String s) {
        super.deleteByID(s);
    }

    @Override
    public void update(ProjectDTO obj) {

        var newProject = findByID(obj.getProjectCode());
        obj.setStatus(newProject.getStatus());

        super.update(obj.getProjectCode(), obj);
    }

    @Override
    public void complete(ProjectDTO project) {
        project.setStatus(Status.COMPLETE);
        super.save(project.getProjectCode(), project);
    }
}
