package ru.sop.core.impl.model.entity.field;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;
import ru.sop.core.impl.metadata.Metadata;
import ru.sop.core.impl.model.data.Data;

@Value
@Builder(toBuilder = true)
public class EntityFieldGetCmd {
    UUID entityId;
    Data data;
    Metadata metadata;
}
