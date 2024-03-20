package ru.sop.core.impl.model;

import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder(toBuilder = true)
public class Audit {
    OffsetDateTime createdDate;
    UUID createdBy;
    OffsetDateTime updatedDate;
    UUID updatedBy;
}
