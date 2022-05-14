package com.cybertek.implementation;

import com.cybertek.dto.UserDTO;
import com.cybertek.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends AbstractMapService<UserDTO, String > implements UserService {
    @Override
    public UserDTO save(UserDTO obj) {
        return super.save(obj.getUserName(), obj);
    }

    @Override
    public UserDTO findByID(String id) {
        return super.findByID(id);
    }

    @Override
    public List<UserDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteByID(String id) {
        super.deleteByID(id);
    }

    @Override
    public void delete(UserDTO obj) {
        super.delete(obj);
    }

    @Override
    public void update(UserDTO obj) {
        super.update(obj.getUserName(), obj);
    }

    @Override
    public List<UserDTO> findManagers() {
        return super.findAll().stream()
                .filter(user -> user.getRole().getDescription().equalsIgnoreCase("manager"))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findEmployees() {
        return super.findAll().stream()
                .filter(user -> user.getRole().getId() == 3)
                .collect(Collectors.toList());
    }
}
