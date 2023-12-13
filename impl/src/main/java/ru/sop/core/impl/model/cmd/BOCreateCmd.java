package ru.sop.core.impl.model.cmd;

import java.util.UUID;
import lombok.Builder;
import ru.sop.core.impl.model.BO;

@Builder(toBuilder = true)
public record BOCreateCmd(
    UUID entityId,
    BO bo
) {
}
