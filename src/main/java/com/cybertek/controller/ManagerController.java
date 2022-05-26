package com.cybertek.controller;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.TaskDTO;
import com.cybertek.dto.UserDTO;
import com.cybertek.enums.Status;
import com.cybertek.service.ProjectService;
import com.cybertek.service.TaskService;
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
        model.addAttribute("projectList", projectService.listAllProjects());
        model.addAttribute("employeeList", userService.listAllByRole("employee"));
        model.addAttribute("taskList", taskService.listAllTasks());

        return "/manager/task_assign-create";
    }

    @PostMapping("/task-create/save")
    public String saveTask(@ModelAttribute("task") TaskDTO task) {

        taskService.save(task);

        return "redirect:/manager/task-create";
    }

    // ----------------- Task - Delete -----------------
    @GetMapping("/task-delete/{id}")
    public String deleteTask(@PathVariable("id") Long id) {

        taskService.delete(id);

        return "redirect:/manager/task-create";
    }

    // ----------------- Task - Update -----------------
    @GetMapping("/task-update/{id}")
    public String editTask(@PathVariable("id") Long id, Model model) {

        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("projectList", projectService.listAllProjects());
        model.addAttribute("employeeList", userService.listAllByRole("employee"));
        model.addAttribute("taskList", taskService.listAllTasks());

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

        UserDTO manager = userService.findByUserName("omer@gmail.com");

        List<ProjectDTO> projectList = projectService.listAllProjectsByManager(manager);

        model.addAttribute("projectList", projectList);

        return "/manager/project-status";
    }

    // ----------------- Project - Complete -----------------
    @GetMapping("/project-complete/{projectCode}")
    public String completeProject(@PathVariable("projectCode") String projectCode) {

        projectService.complete(projectCode);

        return "redirect:/manager/project-status";
    }

}