package com.cybertek.implementation;

import com.cybertek.dto.RoleDTO;
import com.cybertek.entity.Role;
import com.cybertek.mapper.RoleMapper;
import com.cybertek.repository.RoleRepository;
import com.cybertek.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleDTO> listAllRoles() {
        List<Role> roleListEntity = roleRepository.findAll();

        // convert from entity to dto - this is why we need mappers

        return roleListEntity.stream()
                .map(obj -> {
                    return roleMapper.convertToDTO(obj);
                }).collect(Collectors.toList());
    }

    @Override
    public RoleDTO findById(Long id) {
        return null;
    }
}
