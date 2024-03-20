package ru.sop.core.impl.mapper;

import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sop.core.api.dto.data.DataRq;
import ru.sop.core.api.dto.entity.field.EntityFieldCreateRq;
import ru.sop.core.api.dto.entity.field.EntityFieldRs;
import ru.sop.core.api.dto.entity.field.EntityFieldUpdateRq;
import ru.sop.core.impl.config.MapstructConfig;
import ru.sop.core.impl.metadata.Metadata;
import ru.sop.core.impl.model.entity.field.EntityField;
import ru.sop.core.impl.model.entity.field.EntityFieldGetCmd;

@Mapper(
    config = MapstructConfig.class,
    uses = DataMapper.class
)
public interface EntityFieldMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "audit", ignore = true)
    EntityField convert(UUID entityId, EntityFieldCreateRq rq);


    @Mapping(target = "audit", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "type", ignore = true)
    EntityField convert(UUID entityId, UUID id, EntityFieldUpdateRq entity);

    EntityFieldRs convert(EntityField field);

    EntityFieldGetCmd convert(UUID entityId, DataRq data, Metadata metadata);
}
