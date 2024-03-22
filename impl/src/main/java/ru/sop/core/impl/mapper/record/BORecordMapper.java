package ru.sop.core.impl.mapper.record;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sop.core.impl.config.MapstructConfig;
import ru.sop.core.impl.model.bo.BO;
import ru.sop.core.impl.model.bo.BORecord;

@Mapper(config = MapstructConfig.class)
public interface BORecordMapper {

    @Mapping(target = "audit.updatedDate", source = "updatedDate")
    @Mapping(target = "audit.createdDate", source = "createdDate")
    @Mapping(target = "audit.updatedBy", source = "updatedBy")
    @Mapping(target = "audit.createdBy", source = "createdBy")
    BO convert(BORecord rec);

    List<BO> convert(List<BORecord> records);
}
