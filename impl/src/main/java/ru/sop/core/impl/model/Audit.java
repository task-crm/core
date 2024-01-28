package ru.sop.core.impl.model;

import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Audit {
    OffsetDateTime updateDate;
    OffsetDateTime createdDate;
    UUID updatedBy;
    UUID createdBy;
}
