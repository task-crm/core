package ru.sop.core.impl.mapper;

import org.mapstruct.Mapper;
import ru.sop.core.api.dto.data.DataRq;
import ru.sop.core.api.dto.data.PageRs;
import ru.sop.core.impl.config.MapstructConfig;
import ru.sop.core.impl.model.data.Data;
import ru.sop.core.impl.model.data.Page;

@Mapper(config = MapstructConfig.class)
public interface DataMapper {

    PageRs convert(Page<?> page);

    Data convert(DataRq rq);
}
