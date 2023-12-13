package ru.sop.core.impl.model;

import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Builder;

@Builder(toBuilder = true)
public record Audit(
    OffsetDateTime updateDate,
    OffsetDateTime createdDate,
    UUID updatedBy,
    UUID createdBy
) {
}
