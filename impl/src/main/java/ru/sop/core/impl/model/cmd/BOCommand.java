package ru.sop.core.impl.model.cmd;

import java.util.UUID;
import ru.sop.core.impl.metadata.Metadata;
import ru.sop.core.impl.model.BO;

public interface BOCommand {

    UUID getId();

    UUID getEntityId();

    BO getBo();

    Metadata getMetadata();

    BOCommand of(BO bo);
}
