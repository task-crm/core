package ru.sop.core.impl.model.bo;

import java.util.UUID;
import ru.sop.core.impl.metadata.Metadata;

public interface BOCommand {

    UUID getId();

    UUID getEntityId();

    BO getBo();

    Metadata getMetadata();

    BOCommand of(BO bo);
}
