package ru.sop.core.impl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sop.core.api.dto.rs.PageRs;
import ru.sop.core.impl.config.MapstructConfiguration;
import ru.sop.core.impl.model.Page;

@Mapper(config = MapstructConfiguration.class)
public interface PageMapper {

    @Mapping(target = "data", source = "data")
    PageRs convert(Page page);
}
