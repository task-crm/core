package ru.sop.core.impl.service;

import java.util.UUID;
import ru.sop.core.impl.model.BO;
import ru.sop.core.impl.model.Page;
import ru.sop.core.impl.model.query.Query;

public interface BOSearchService {

    Page getPage(Query query);

    BO getById(UUID id);
}
