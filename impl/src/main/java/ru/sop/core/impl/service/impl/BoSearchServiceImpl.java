package ru.sop.core.impl.service.impl;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sop.core.api.dto.data.DataRq;
import ru.sop.core.api.dto.data.DataRs;
import ru.sop.core.impl.model.bo.BO;
import ru.sop.core.impl.repository.BORepository;
import ru.sop.core.impl.service.BOSearchService;

@Service
@RequiredArgsConstructor
public class BoSearchServiceImpl implements BOSearchService {
    private final BORepository boRepository;

    @Override
    public DataRs getData(UUID entityId, DataRq rq) {
        return null;
    }

    @Override
    public BO getById(UUID id) {
        return boRepository.getById(id);
    }
}
