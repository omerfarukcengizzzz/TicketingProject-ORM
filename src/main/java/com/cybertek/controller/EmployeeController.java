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

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    //DI
    @Autowired
    UserService userService;
    @Autowired
    ProjectService projectService;
    @Autowired
    TaskService taskService;

    // ----------------- Task Status -----------------
    @GetMapping("/pending-tasks")
    public String taskStatus(Model model) {

        model.addAttribute("taskList", taskService.listAllTasksByStatusIsNot(Status.COMPLETED));

        return "/employee/pending-tasks";
    }

    // ----------------- Task Status - Update -----------------
    @GetMapping("/pending-tasks-update/{id}")
    public String taskStatusUpdate(@PathVariable("id") Long id, Model model) {

        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("project", projectService.listAllProjects());
        model.addAttribute("taskList", taskService.listAllTasksByStatusIsNot(Status.COMPLETED));
        model.addAttribute("projectList", projectService.listAllProjects());
        model.addAttribute("employeeList", userService.listAllByRole("employee"));
        model.addAttribute("statusList", Status.values());

        return "/employee/pending-tasks-update";
    }

    @PostMapping("/pending-tasks-update/{id}")
    public String taskStatusSave(@PathVariable("id") Long id, @ModelAttribute("task") TaskDTO taskDTO) {

        taskService.save(taskDTO);

        return "redirect:/employee/pending-tasks";
    }


}
