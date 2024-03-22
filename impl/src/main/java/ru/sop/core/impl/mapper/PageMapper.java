package ru.sop.core.impl.mapper;

import org.mapstruct.Mapper;
import ru.sop.core.api.dto.page.PageRs;
import ru.sop.core.api.dto.page.PageSelectorRq;
import ru.sop.core.impl.config.MapstructConfig;
import ru.sop.core.impl.model.page.Page;
import ru.sop.core.impl.model.page.PageSelector;

@Mapper(config = MapstructConfig.class)
public interface PageMapper {

    PageRs convert(Page<?> page);

    PageSelector convert(PageSelectorRq rq);
}
