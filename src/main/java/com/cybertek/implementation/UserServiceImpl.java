package com.cybertek.implementation;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.TaskDTO;
import com.cybertek.dto.UserDTO;
import com.cybertek.entity.Project;
import com.cybertek.entity.Task;
import com.cybertek.entity.User;
import com.cybertek.exception.TicketingProjectException;
import com.cybertek.mapper.UserMapper;
import com.cybertek.repository.ProjectRepository;
import com.cybertek.repository.UserRepository;
import com.cybertek.service.ProjectService;
import com.cybertek.service.TaskService;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<UserDTO> listAllUsers() {
        List<User> userList = userRepository.findAll(Sort.by("firstName"));

        return userList.stream()
                .map(obj -> userMapper.convertToDTO(obj))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {
        User user = userRepository.findByUserName(username);

        return userMapper.convertToDTO(user);
    }

    @Override
    public void save(UserDTO dto) {
        User user = userMapper.convertToEntity(dto);
        userRepository.save(user);
    }

    @Override
    public UserDTO update(UserDTO dto) {
        // find current user
        User user = userRepository.findByUserName(dto.getUserName());

        // convert to entity
        User entityUser = userMapper.convertToEntity(dto);

        // set id on entityUser
        entityUser.setId(user.getId());

        // save updated user
        userRepository.save(entityUser);

        return findByUserName(dto.getUserName());
    }

    // soft delete (good practice)
    @Override
    public void delete(String username) throws TicketingProjectException {
        User user = userRepository.findByUserName(username);

        if (user == null) {
            throw new TicketingProjectException("User Does Not Exists!");
        }

        if (!checkIfUserCanBeDeleted(user)) {
            throw new TicketingProjectException("User cannot be deleted! It's linked by a project or a task!");
        }

        // before deleting, we could change the unique username to a different name, so we could create a new user with the same username
        user.setUserName(user.getUserName() + '-' + user.getId());

        user.setIsDeleted(true);

/*
        // we could also delete the whole related tasks and projects instead of throwing an exception
        List<Task> taskList = taskService.listAllByAssignedEmployee(userMapper.convertToDTO(user));
        taskList.stream()
                        .forEach(task -> task.setIsDeleted(true));

        List<Project> projectList = projectRepository.findByAssignedManager(user);
        projectList.stream()
                        .forEach(project -> projectService.delete(project.getProjectCode()));
*/

        userRepository.save(user);
    }

    @Override
    public void deleteByUserName(String username) {
        // deletes from the database (not a good practice, we shouldn't delete data from database)
        userRepository.deleteByUserName(username);
    }

    @Override
    public List<UserDTO> listAllByRole(String role) {
        List<User> userList = userRepository.findAllByRoleDescriptionIgnoreCase(role);

        return userList.stream()
                .map(obj -> userMapper.convertToDTO(obj))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean checkIfUserCanBeDeleted(User user) {

        switch (user.getRole().getDescription()) {
            case "Manager":
                List<ProjectDTO> projectDTOList = projectService.listAllProjectsByManager(userMapper.convertToDTO(user));
                return projectDTOList.size() == 0;  // check if it is empty or not
            case "Employee":
                List<TaskDTO> taskDTOList = taskService.readAllByAssignedEmployee(user);
                return taskDTOList.size() == 0;
            default:
                return true;
        }

    }
}
