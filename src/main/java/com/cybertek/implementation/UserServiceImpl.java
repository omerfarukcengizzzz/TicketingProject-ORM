package com.cybertek.implementation;

import com.cybertek.dto.UserDTO;
import com.cybertek.entity.User;
import com.cybertek.mapper.UserMapper;
import com.cybertek.repository.UserRepository;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDTO> listAllUsers() {
        List<User> userList = userRepository.findAll(Sort.by("firstName"));

        return userList.stream()
                .map(obj -> userMapper.convertToDTO(obj))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {
        User user = userRepository.findByUserName(username);

        return userMapper.convertToDTO(user);
    }

    @Override
    public void save(UserDTO dto) {
        User user = userMapper.convertToEntity(dto);
        userRepository.save(user);
    }

    @Override
    public UserDTO update(UserDTO dto) {
        // find current user
        User user = userRepository.findByUserName(dto.getUserName());

        // convert to entity
        User entityUser = userMapper.convertToEntity(dto);

        // set id on entityUser
        entityUser.setId(user.getId());

        // save updated user
        userRepository.save(entityUser);

        return findByUserName(dto.getUserName());
    }

    // soft delete (good practice)
    @Override
    public void delete(String username) {
        User user = userRepository.findByUserName(username);
        user.setIsDeleted(true);
        userRepository.save(user);
    }

    @Override
    public void deleteByUserName(String username) {
        // deletes from the database (not a good practice, we shouldn't delete data from database)
        userRepository.deleteByUserName(username);
    }
}
