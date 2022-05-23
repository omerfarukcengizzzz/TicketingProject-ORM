package com.cybertek.controller;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.TaskDTO;
import com.cybertek.dto.UserDTO;
import com.cybertek.enums.Status;
import com.cybertek.service.ProjectService;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    // DI
    @Autowired
    ProjectService projectService;
    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;

    // ----------------- Task Creation -----------------
    @GetMapping("/task-create")
    public String createTask(Model model) {

        model.addAttribute("task", new TaskDTO());
        model.addAttribute("projectList", projectService.findAll());
        model.addAttribute("employeeList", userService.findEmployees());
        model.addAttribute("taskList", taskService.findAll());

        return "/manager/task_assign-create";
    }

    @PostMapping("/task-create/save")
    public String saveTask(@ModelAttribute("task") TaskDTO task) {

        task.setAssignedDate(LocalDate.now());
        task.setStatus(Status.OPEN);
        task.setId(UUID.randomUUID().getMostSignificantBits());
        taskService.save(task);

        return "redirect:/manager/task-create";
    }

    // ----------------- Task - Delete -----------------
    @GetMapping("/task-delete/{id}")
    public String deleteTask(@PathVariable("id") Long id) {

        taskService.deleteByID(id);

        return "redirect:/manager/task-create";
    }

    // ----------------- Task - Update -----------------
    @GetMapping("/task-update/{id}")
    public String editTask(@PathVariable("id") Long id, Model model) {

        model.addAttribute("task", taskService.findByID(id));
        model.addAttribute("projectList", projectService.findAll());
        model.addAttribute("employeeList", userService.findEmployees());
        model.addAttribute("taskList", taskService.findAll());

        return "/manager/task_assign-update";
    }

    @PostMapping("/task-update/{id}")
    public String updateTask(@ModelAttribute("task") TaskDTO task) {

        taskService.update(task);

        return "redirect:/manager/task-create";
    }

    // ----------------- Project Status -----------------
    @GetMapping("/project-status")
    public String getProjectStatus(Model model){

        UserDTO manager = userService.findByUserName("john@cybertek.com");

        List<ProjectDTO> projects = getCountedListOfProjectDTO(manager);

        model.addAttribute("projectList", projects);

        projects.stream()
                        .filter(p -> p.getUnfinishedTasks() > 0).forEach(p -> p.setStatus(Status.IN_PROGRESS));
        projects.stream()
                        .filter(p -> p.getUnfinishedTasks() == 0 && p.getCompletedTasks() > 0).forEach(p -> p.setStatus(Status.COMPLETE));

        return "/manager/project-status";
    }

    List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {

        return projectService.findAll()
                .stream()
                .filter(p -> p.getAssignedManager().equals(manager))
                .map(p -> {

                    List<TaskDTO> taskList = taskService.findTaskByManager(manager);
                    int completeCounter = (int) taskList.stream()
                            .filter(t -> t.getProject().equals(p) && t.getStatus() == Status.COMPLETE)
                            .count();
                    int incompleteCounter = (int) taskList.stream()
                            .filter(t -> t.getProject().equals(p) && t.getStatus() != Status.COMPLETE)
                            .count();

                    p.setCompletedTasks(completeCounter);
                    p.setUnfinishedTasks(incompleteCounter);

                    return p;

//                    return new ProjectDTO(p.getProjectName(), p.getProjectCode(), userService.findByID(p.getAssignedManager().getUserName()),
//                            p.getStartDate(), p.getEndDate(), p.getProjectDetails(), p.getStatus(), completeCounter, incompleteCounter);
                })
                .collect(Collectors.toList());
    }


    // ----------------- Project - Complete -----------------
    @GetMapping("/project-complete/{projectCode}")
    public String completeProject(@PathVariable("projectCode") String projectCode) {

        var project = projectService.findByID(projectCode);
        var manager = project.getAssignedManager();
        var taskList = taskService.findTaskByManager(manager);

        taskList.stream()
                        .filter(t -> t.getProject().getProjectCode().equals(projectCode))
                        .filter(t -> t.getStatus() != Status.COMPLETE)
                        .forEach(t -> t.setStatus(Status.COMPLETE));

        projectService.complete(projectService.findByID(projectCode));

        return "redirect:/manager/project-status";
    }

}