package ru.sop.core.impl.repository;

import java.util.UUID;
import ru.sop.core.impl.model.bo.BO;

public interface BORepository {
    void create(BO businessObject);

    void update(BO bo);

    void deleteById(UUID id);

    BO getById(UUID id);
}
