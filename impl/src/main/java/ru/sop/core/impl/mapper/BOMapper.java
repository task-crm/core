package ru.sop.core.impl.mapper;

import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sop.core.api.dto.rq.BOCreateRq;
import ru.sop.core.api.dto.rq.BOUpdateRq;
import ru.sop.core.api.dto.rs.BORs;
import ru.sop.core.impl.config.MapstructConfiguration;
import ru.sop.core.impl.model.BO;
import ru.sop.core.impl.model.cmd.BOCreateCmd;
import ru.sop.core.impl.model.cmd.BOUpdateCmd;


@Mapper(config = MapstructConfiguration.class)
public interface BOMapper {
    @Mapping(target = "bo.id", ignore = true)
    @Mapping(target = "bo.audit", ignore = true)
    @Mapping(target = "bo.name", source = "rq.name")
    @Mapping(target = "bo.data", source = "rq.data")
    @Mapping(target = "bo.references", source = "rq.references")
    BOCreateCmd convert(BOCreateRq rq, UUID entityId);

    @Mapping(target = "bo.name", source = "rq.name")
    @Mapping(target = "bo.data", source = "rq.data")
    @Mapping(target = "bo.references", source = "rq.references")
    BOUpdateCmd convert(UUID id, BOUpdateRq rq, UUID entityId);

    BORs convert(BO bo);
}
