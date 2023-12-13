package ru.sop.core.impl.repository;

import java.util.UUID;
import ru.sop.core.impl.model.BO;

public interface BORepository {
    BO create(BO businessObject);

    BO update(UUID id, BO bo);

    void deleteById(UUID id);

    BO getById(UUID id);
}
