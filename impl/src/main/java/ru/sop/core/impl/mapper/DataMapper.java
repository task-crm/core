package ru.sop.core.impl.mapper;

import org.mapstruct.Mapper;
import ru.sop.core.api.dto.data.DataRq;
import ru.sop.core.api.dto.data.DataRs;
import ru.sop.core.impl.config.MapstructConfiguration;
import ru.sop.core.impl.model.data.Data;
import ru.sop.core.impl.model.data.DataResult;

@Mapper(config = MapstructConfiguration.class)
public interface DataMapper {

    DataRs convert(DataResult dataResult);

    Data convert(DataRq rq);
}
