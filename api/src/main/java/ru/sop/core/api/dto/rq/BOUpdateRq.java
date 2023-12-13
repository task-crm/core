package ru.sop.core.api.dto.rq;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;
import lombok.Builder;

@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public record BOUpdateRq(
    String name,
    Map<String, Object> data,
    Map<String, Object> references
) {
}
