package com.cybertek.controller;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.UserDTO;
import com.cybertek.exception.TicketingProjectException;
import com.cybertek.service.ProjectService;
import com.cybertek.service.RoleService;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    // DI
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    ProjectService projectService;

    // ----------------- User Creation -----------------
    @GetMapping({"/user-create", "/user-add", "/user-initialize"})
    public String userCreate(Model model) {

        model.addAttribute("user", new UserDTO());
        model.addAttribute("roleList", roleService.listAllRoles());
        model.addAttribute("userList", userService.listAllUsers());

        return "/admin/user-create";
    }

    @PostMapping("/user-create/save")
    public String saveUser(@ModelAttribute("user") UserDTO user, Model model) {

        userService.save(user);

        return "redirect:/admin/user-create";
    }


    // ----------------- User - Update -----------------
    @GetMapping("/user-update/{username}")
    public String editUser(@PathVariable("username") String username, Model model) {

        model.addAttribute("user", userService.findByUserName(username));
        model.addAttribute("userList", userService.listAllUsers());
        model.addAttribute("roleList", roleService.listAllRoles());

        return "/admin/user-update";
    }

    @PostMapping("/user-update/{username}")
    public String updateUser(@PathVariable("username") String username, Model model, @ModelAttribute("user") UserDTO user) {

        userService.update(user);

        return "redirect:/admin/user-create";
    }

    // ----------------- User - Delete -----------------
    @GetMapping("/user-delete/{username}")
    public String deleteUser(@PathVariable("username") String username) {

        try {
            userService.delete(username);
        } catch (TicketingProjectException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/admin/user-create";
    }


    // ----------------- Project Creation -----------------
    @GetMapping("/project-create")
    public String projectCreate(Model model) {

        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("projectList", projectService.listAllProjects());
        model.addAttribute("managerList", userService.listAllByRole("manager"));

        return "/admin/project-create";
    }

    @PostMapping("/project-create/save")
    public String projectUpdate(@ModelAttribute("project") ProjectDTO project) {

        projectService.save(project);

        return "redirect:/admin/project-create";
    }

    // ----------------- Project - Delete -----------------
    @GetMapping("/project-delete/{projectCode}")
    public String deleteProject(@PathVariable("projectCode") String projectCode) {

        projectService.delete(projectCode);

        return "redirect:/admin/project-create";
    }

    // ----------------- Project - Complete -----------------
    @GetMapping("/project-complete/{projectCode}")
    public String completeProject(@PathVariable("projectCode") String projectCode) {

        projectService.complete(projectService.getByProjectCode(projectCode).getProjectCode());

        return "redirect:/admin/project-create";
    }

    // ----------------- Project - Update -----------------
    @GetMapping("/project-update/{projectCode}")
    public String editProject(@PathVariable("projectCode") String projectCode, Model model) {

        model.addAttribute("project", projectService.getByProjectCode(projectCode));
        model.addAttribute("managerList", userService.listAllByRole("manager"));
        model.addAttribute("projectList", projectService.listAllProjects());

        return "/admin/project-update";
    }

    @PostMapping("/project-update/{projectCode}")
    public String updateProject(@PathVariable("projectCode") String projectCode, @ModelAttribute("project") ProjectDTO project) {

        projectService.update(project);

        return "redirect:/admin/project-create";
    }


}