package com.tohjiwa.teamsync.server.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsUserConfig;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Converter
public class PwsUserConfigConverterJson implements AttributeConverter<PwsUserConfig, String> {
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(PwsUserConfig wspUserConfig) {
        try {
            return objectMapper.writeValueAsString(wspUserConfig);
        } catch (JsonProcessingException ex) {
            throw new IllegalArgumentException("Could not convert to json", ex);
        }
    }

    @Override
    public PwsUserConfig convertToEntityAttribute(String jsonString) {
        if (jsonString == null) {
            return null;
        }
        jsonString = StringEscapeUtils.unescapeJson(jsonString).replaceAll("^\"|\"$", "");

        try {
            return objectMapper.readValue(jsonString, PwsUserConfig.class);
        } catch (JsonProcessingException ex) {
            throw new IllegalArgumentException("Could not convert json to object", ex);
        }
    }
}
