package com.cybertek.converter;

import com.cybertek.dto.RoleDTO;
import com.cybertek.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding // whenever the spring framework needs to convert an object from the view, it will
                                // automatically come here because of the annotation. The Converter<S, T> is available
                                // thanks to Spring Framework. (Source, Target)
public class RoleDtoConverter implements Converter<String, RoleDTO> {

    @Lazy   // added because of the circular dependency
    @Autowired
    RoleService roleService;

    @Override
    public RoleDTO convert(String source) {
        Long id = Long.parseLong(source);
        return roleService.findById(id);
    }

}
