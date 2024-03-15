package ru.sop.core.api.dto.bo;

import java.util.Map;
import java.util.UUID;
import lombok.Builder;

@Builder(toBuilder = true)
public record BORs(
    UUID id,
    String name,
    Map<String, Object> data,
    Map<String, Object> references
) {
}
