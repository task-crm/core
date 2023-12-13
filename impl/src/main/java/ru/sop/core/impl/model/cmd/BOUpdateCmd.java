package ru.sop.core.impl.model.cmd;

import java.util.UUID;
import lombok.Builder;
import ru.sop.core.impl.model.BO;

@Builder(toBuilder = true)
public record BOUpdateCmd(
    UUID id,
    UUID entityId,
    BO bo
) {
}
