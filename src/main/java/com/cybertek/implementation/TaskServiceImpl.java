package com.cybertek.implementation;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.TaskDTO;
import com.cybertek.entity.Task;
import com.cybertek.entity.User;
import com.cybertek.enums.Status;
import com.cybertek.mapper.ProjectMapper;
import com.cybertek.mapper.TaskMapper;
import com.cybertek.repository.TaskRepository;
import com.cybertek.repository.UserRepository;
import com.cybertek.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public TaskDTO findById(Long id) {
        Optional<Task> task = taskRepository.findById(id);

        if (task.isPresent()) {
            return taskMapper.convertToDTO(task.get());
        } else {
            return null;
        }
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
        Optional<Task> task = taskRepository.findById(taskDTO.getId());
        Task convertedTask = taskMapper.convertToEntity(taskDTO);

        if (task.isPresent()) {
            convertedTask.setId(task.get().getId());
            convertedTask.setStatus(task.get().getStatus());
            convertedTask.setAssignedDate(task.get().getAssignedDate());
            taskRepository.save(convertedTask);
        }

    }

    @Override
    public void delete(Long id) {
        Optional<Task> foundTask = taskRepository.findById(id);

        if (foundTask.isPresent()) {
            foundTask.get().setIsDeleted(true);
        }

        taskRepository.save(foundTask.get());
    }

    @Override
    public Integer totalCompletedTasks(String projectCode) {
        return taskRepository.totalCompletedTasks(projectCode);
    }

    @Override
    public Integer totalNonCompletedTasks(String projectCode) {
        return taskRepository.totalNonCompletedTasks(projectCode);
    }

    @Override
    public void deleteByProject(ProjectDTO projectDTO) {
        List<TaskDTO> taskDTOList = listAllTasksByProject(projectDTO);

        taskDTOList.stream()
                .forEach(taskDTO -> delete(taskDTO.getId()));
    }

    @Override
    public List<TaskDTO> listAllTasksByProject(ProjectDTO projectDTO) {
        List<Task> taskList = taskRepository.findAllByProject(projectMapper.convertToEntity(projectDTO));

        return taskList.stream()
                .map(taskMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> listAllTasksByStatusIsNot(Status status) {
        User employee = userRepository.findByUserName("employee@gmail.com");

        List<Task> taskList = taskRepository.findAllByStatusIsNotAndAssignedEmployee(status, employee);

        return taskList.stream()
                .map(taskMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> listAllTasksByProjectManager() {
        User manager = userRepository.findByUserName("omer@gmail.com");
        List<Task> taskList = taskRepository.findAllByProjectAssignedManager(manager);

        return taskList.stream()
                .map(taskMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void updateStatus(TaskDTO taskDTO) {
        Optional<Task> task = taskRepository.findById(taskDTO.getId());

        if (task.isPresent()) {
            task.get().setStatus(taskDTO.getStatus());
            taskRepository.save(task.get());
        }

    }

}
