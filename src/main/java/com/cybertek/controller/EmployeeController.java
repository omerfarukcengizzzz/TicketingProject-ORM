package com.cybertek.controller;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.TaskDTO;
import com.cybertek.enums.Status;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
//
//    //DI
//    @Autowired
//    UserService userService;
//    @Autowired
//    ProjectService projectService;
//    @Autowired
//    TaskService taskService;
//
//    // ----------------- Task Status -----------------
//    @GetMapping("/pending-tasks")
//    public String taskStatus(Model model) {
//
//        model.addAttribute("task", new TaskDTO());
//        model.addAttribute("projectList", projectService.findAll());
//        model.addAttribute("employeeList", userService.findEmployees());
//        model.addAttribute("taskList", taskService.findAll());
//        model.addAttribute("statusList", Status.values());
//
//        return "/employee/pending-tasks";
//    }
//
//    @PostMapping("/pending-tasks/save")
//    public String taskStatusSave() {
//
//        return "redirect:/employee/pending-tasks";
//    }
//
//    // ----------------- Task Status - Update -----------------
//    @GetMapping("/pending-tasks-update/{id}")
//    public String taskStatusUpdate(@PathVariable("id") Long id, Model model) {
//
//        TaskDTO task = taskService.findByID(id);
//
//        model.addAttribute("task", task);
//        model.addAttribute("project", projectService.findAll());
//        model.addAttribute("taskList", taskService.findAll());
//        model.addAttribute("projectList", projectService.findAll());
//        model.addAttribute("employeeList", userService.findEmployees());
//        model.addAttribute("statusList", Status.values());
//
//        return "/employee/pending-tasks-update";
//    }
//
//    @PostMapping("/pending-tasks-update/{id}")
//    public String taskStatusUpdateSave(@PathVariable("id") Long id, @ModelAttribute("task") TaskDTO task) {
//
//        ProjectDTO project = task.getProject();
//        projectService.save(project);
//
//        taskService.update(task);
//
//        return "redirect:/employee/pending-tasks";
//    }


}
