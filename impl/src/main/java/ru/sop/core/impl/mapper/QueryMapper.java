package ru.sop.core.impl.mapper;

import org.mapstruct.Mapper;
import ru.sop.core.api.dto.rq.QueryRq;
import ru.sop.core.impl.config.MapstructConfiguration;
import ru.sop.core.impl.model.query.Query;

@Mapper(config = MapstructConfiguration.class)
public interface QueryMapper {

    Query convert(QueryRq rq);
}
