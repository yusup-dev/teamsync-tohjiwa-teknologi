package com.tohjiwa.teamsync.server.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tohjiwa.teamsync.server.model.workspace.personal.PwsSetting;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Converter
public class PwsSettingConverterJson implements AttributeConverter<PwsSetting, String> {
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(PwsSetting setting) {
        if (setting == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(setting);
        } catch (JsonProcessingException ex) {
            throw new IllegalArgumentException("Could not convert to json", ex);
        }
    }

    @Override
    public PwsSetting convertToEntityAttribute(String jsonString) {
        if (jsonString == null) {
            return null;
        }
        jsonString = StringEscapeUtils.unescapeJson(jsonString).replaceAll("^\"|\"$", "");

        try {
            return objectMapper.readValue(jsonString, PwsSetting.class);
        } catch (JsonProcessingException ex) {
            throw new IllegalArgumentException("Could not convert json to object", ex);
        }
    }
}
