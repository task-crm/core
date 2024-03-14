package ru.sop.core.impl.helper.jooq;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.jooq.JSONB;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonbHelper {
    private static final TypeReference<Map<String, Object>> DATA_TYPE_REFERENCE = new TypeReference<Map<String, Object>>() {
    };

    private final ObjectMapper objectMapper;


    public JSONB toJsonB(String json) {
        return json == null ? null : JSONB.valueOf(json);
    }

    @SneakyThrows
    public JSONB toJsonB(Object object) {
        return object == null ? null : JSONB.valueOf(objectMapper.writeValueAsString(object));
    }

    public Map<String, Object> fromJsonB(JSONB jsonb) {
        return fromJsonB(jsonb, DATA_TYPE_REFERENCE);
    }

    @SneakyThrows
    public <T> T fromJsonB(JSONB jsonb, TypeReference<T> typeReference) {
        return jsonb != null && StringUtils.isNotBlank(jsonb.data())
            ? objectMapper.readValue(jsonb.data(), typeReference)
            : null;

    }
}
