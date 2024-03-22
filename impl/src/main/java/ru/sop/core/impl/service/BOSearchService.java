package ru.sop.core.impl.service;

import java.util.UUID;
import ru.sop.core.impl.metadata.Metadata;
import ru.sop.core.impl.model.bo.BO;
import ru.sop.core.impl.model.page.Page;
import ru.sop.core.impl.model.page.PageSelector;

public interface BOSearchService {

    Page<BO> getPage(Metadata metadata, UUID entityId, PageSelector pageSelector);

    BO getOne(UUID id);
}
