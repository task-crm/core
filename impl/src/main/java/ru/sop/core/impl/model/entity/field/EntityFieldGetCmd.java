package ru.sop.core.impl.model.entity.field;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;
import ru.sop.core.impl.model.page.PageSelector;

@Value
@Builder(toBuilder = true)
public class EntityFieldGetCmd {
    UUID entityId;
    PageSelector pageSelector;
}
