package ru.sop.core.impl.repository;

import java.util.UUID;
import ru.sop.core.impl.model.BO;

public interface BORepository {
    BO create(BO businessObject);

    BO update(BO bo);

    void deleteById(UUID id);

    BO getById(UUID id);
}
