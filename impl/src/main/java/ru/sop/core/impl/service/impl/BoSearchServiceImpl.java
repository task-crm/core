package ru.sop.core.impl.service.impl;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sop.core.impl.model.BO;
import ru.sop.core.impl.model.Page;
import ru.sop.core.impl.model.query.Query;
import ru.sop.core.impl.repository.BORepository;
import ru.sop.core.impl.service.BOSearchService;

@Service
@RequiredArgsConstructor
public class BoSearchServiceImpl implements BOSearchService {
    private final BORepository boRepository;
    @Override
    public Page getPage(Query query) {
        return null;
    }

    @Override
    public BO getById(UUID id) {
        return boRepository.getById(id);
    }
}
