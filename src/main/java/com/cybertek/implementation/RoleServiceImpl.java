package com.cybertek.implementation;

import com.cybertek.dto.RoleDTO;
import com.cybertek.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends AbstractMapService<RoleDTO, Long> implements RoleService {
    @Override
    public RoleDTO save(RoleDTO obj) {
        return super.save(obj.getId(), obj);
    }

    @Override
    public RoleDTO findByID(Long id) {
        return super.findByID(id);
    }

    @Override
    public List<RoleDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteByID(Long id) {
        super.deleteByID(id);
    }

    @Override
    public void delete(RoleDTO obj) {
        super.delete(obj);
    }

    @Override
    public void update(RoleDTO obj) {
        super.update(obj.getId(), obj);
    }

}
