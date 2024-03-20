package ru.sop.core.impl.mapper;

import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sop.core.api.dto.bo.BOCreateRq;
import ru.sop.core.api.dto.bo.BORs;
import ru.sop.core.api.dto.bo.BOUpdateRq;
import ru.sop.core.impl.config.MapstructConfig;
import ru.sop.core.impl.metadata.Metadata;
import ru.sop.core.impl.model.bo.BO;
import ru.sop.core.impl.model.bo.BOCreateCmd;
import ru.sop.core.impl.model.bo.BOUpdateCmd;


@Mapper(config = MapstructConfig.class)
public interface BOMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bo.audit", ignore = true)
    @Mapping(target = "bo.name", source = "rq.name")
    @Mapping(target = "bo.data", source = "rq.data")
    @Mapping(target = "bo.references", source = "rq.references")
    BOCreateCmd convert(BOCreateRq rq, UUID entityId, Metadata metadata);

    @Mapping(target = "bo.name", source = "rq.name")
    @Mapping(target = "bo.data", source = "rq.data")
    @Mapping(target = "bo.references", source = "rq.references")
    BOUpdateCmd convert(UUID id, BOUpdateRq rq, UUID entityId, Metadata metadata);

    BORs convert(BO bo);
}
