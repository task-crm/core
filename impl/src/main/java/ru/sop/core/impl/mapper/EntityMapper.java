package ru.sop.core.impl.mapper;

import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sop.core.api.dto.entity.EntityCreateRq;
import ru.sop.core.api.dto.entity.EntityRs;
import ru.sop.core.api.dto.entity.EntityUpdateRq;
import ru.sop.core.impl.config.MapstructConfig;
import ru.sop.core.impl.model.entity.Entity;

@Mapper(config = MapstructConfig.class)
public interface EntityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "audit", ignore = true)
    Entity convert(EntityCreateRq rq);

    EntityRs convert(Entity entity);

    @Mapping(target = "audit", ignore = true)
    @Mapping(target = "name", ignore = true)
    Entity convert(UUID entityId, EntityUpdateRq rq);
}
