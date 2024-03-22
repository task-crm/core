package ru.sop.core.impl.service.impl;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sop.core.impl.metadata.Metadata;
import ru.sop.core.impl.model.bo.BO;
import ru.sop.core.impl.model.page.Page;
import ru.sop.core.impl.model.page.PageSelector;
import ru.sop.core.impl.repository.BORepository;
import ru.sop.core.impl.service.BOSearchService;

@Service
@RequiredArgsConstructor
public class BoSearchServiceImpl implements BOSearchService {
    private final BORepository boRepository;

    @Override
    public Page<BO> getPage(Metadata metadata, UUID entityId, PageSelector pageSelector) {
        return boRepository.getPage(pageSelector);
    }

    @Override
    public BO getOne(UUID id) {
        return boRepository.getOne(id);
    }
}
