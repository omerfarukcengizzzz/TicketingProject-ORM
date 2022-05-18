package com.cybertek.mapper;

import com.cybertek.dto.RoleDTO;
import com.cybertek.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Role convertToEntity(RoleDTO roleDTO) {
        return modelMapper.map(roleDTO, Role.class);
    }

    public RoleDTO convertToDTO(Role role) {
        return modelMapper.map(role, RoleDTO.class);
    }

}
