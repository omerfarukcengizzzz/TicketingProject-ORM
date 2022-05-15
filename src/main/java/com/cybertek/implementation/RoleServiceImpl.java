package com.cybertek.implementation;

import com.cybertek.dto.RoleDTO;
import com.cybertek.entity.Role;
import com.cybertek.repository.RoleRepository;
import com.cybertek.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleDTO> listAllRoles() {
        List<Role> roleListEntity = roleRepository.findAll();

        // convert from entity to dto and return it

        return null;
    }

    @Override
    public RoleDTO findById(Long id) {
        return null;
    }
}
