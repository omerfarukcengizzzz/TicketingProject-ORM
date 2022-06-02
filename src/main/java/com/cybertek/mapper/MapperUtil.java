package com.cybertek.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class MapperUtil {

    @Autowired
    private ModelMapper modelMapper;

    public <T> T convert(Object objectToBeConverted, T convertedObject) {
        return modelMapper.map(objectToBeConverted, (Type) convertedObject.getClass());
    }

/*
    // the methods are almost the same, instead we could create only one method to convert both
    public <T> T convertToEntity(Object dto, T entity) {
        return modelMapper.map(dto, (Type) entity.getClass());
    }

    public <T> T convertToDto(Object entity, T dto) {
        return modelMapper.map(entity, (Type) dto.getClass());
    }
*/

}
