package com.tohjiwa.teamsync.server.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tohjiwa.teamsync.server.model.Url;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Converter
public class PwsUrlConverterJson implements AttributeConverter<List<Url>, String> {
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<Url> pojo) {
        try {
            return objectMapper.writeValueAsString(pojo);
        } catch (JsonProcessingException ex) {
            throw new IllegalArgumentException("Could not convert to json", ex);
        }
    }

    @Override
    public List<Url> convertToEntityAttribute(String jsonString) {
        if (jsonString == null) {
            return null;
        }
        jsonString = StringEscapeUtils.unescapeJson(jsonString).replaceAll("^\"|\"$", "");

        try {
            JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, Url.class);
            return objectMapper.readValue(jsonString, type);
        } catch (JsonProcessingException ex) {
            throw new IllegalArgumentException("Could not convert json to object", ex);
        }
    }
}
