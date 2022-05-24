package com.cybertek.converter;

import com.cybertek.dto.UserDTO;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class UserDTOConverter implements Converter<String, UserDTO> {

    @Lazy
    @Autowired
    UserService userService;

    @Override
    public UserDTO convert(String source) {
        return userService.findByUserName(source);
    }

}
