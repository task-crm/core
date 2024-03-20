package ru.sop.core.impl.mapper.record;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sop.core.impl.config.MapstructConfig;
import ru.sop.core.impl.model.entity.field.EntityField;
import ru.sop.core.impl.model.entity.field.EntityFieldRecord;

@Mapper(config = MapstructConfig.class)
public interface EntityFieldRecordMapper {

    @Mapping(target = "audit.updatedDate", source = "updatedDate")
    @Mapping(target = "audit.createdDate", source = "createdDate")
    @Mapping(target = "audit.updatedBy", source = "updatedBy")
    @Mapping(target = "audit.createdBy", source = "createdBy")
    EntityField convert(EntityFieldRecord field);

    List<EntityField> convert(List<EntityFieldRecord> fields);
}
