package com.tohjiwa.teamsync.server.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tohjiwa.teamsync.server.model.App;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Converter
public class PwsAppConverterJson implements AttributeConverter<List<App>, String> {
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<App> pojo) {
        try {
            return objectMapper.writeValueAsString(pojo);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Could not convert to json", e);
        }
    }

    @Override
    public List<App> convertToEntityAttribute(String jsonString) {
        if (jsonString == null) {
            return null;
        }
        jsonString = StringEscapeUtils.unescapeJson(jsonString).replaceAll("^\"|\"$", "");

        try {
            JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, App.class);
            return objectMapper.readValue(jsonString, type);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Could not convert json to object", e);
        }
    }
}
