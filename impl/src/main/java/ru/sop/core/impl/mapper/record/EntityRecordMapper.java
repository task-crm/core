package ru.sop.core.impl.mapper.record;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sop.core.impl.config.MapstructConfig;
import ru.sop.core.impl.model.entity.Entity;
import ru.sop.core.impl.model.entity.EntityRecord;

@Mapper(config = MapstructConfig.class)
public interface EntityRecordMapper {

    @Mapping(target = "audit.updatedDate", source = "updatedDate")
    @Mapping(target = "audit.createdDate", source = "createdDate")
    @Mapping(target = "audit.updatedBy", source = "updatedBy")
    @Mapping(target = "audit.createdBy", source = "createdBy")
    Entity convert(EntityRecord entity);

    List<Entity> convert(List<EntityRecord> entities);
}
