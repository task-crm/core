package ru.sop.core.impl.model.bo;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;
import ru.sop.core.impl.metadata.Metadata;

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
