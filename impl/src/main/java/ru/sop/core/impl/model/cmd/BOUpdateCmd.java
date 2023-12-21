package ru.sop.core.impl.model.cmd;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;
import ru.sop.core.impl.metadata.Metadata;
import ru.sop.core.impl.model.BO;

@Value
@Builder(toBuilder = true)
public class BOUpdateCmd implements BOCommand {
    UUID id;
    UUID entityId;
    BO bo;
    Metadata metadata;

    public BOUpdateCmd of(BO bo) {
        return this.toBuilder()
            .bo(bo)
            .build();
    }
}
