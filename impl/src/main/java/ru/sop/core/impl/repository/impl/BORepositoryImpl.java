package ru.sop.core.impl.repository.impl;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.sop.core.impl.model.BO;
import ru.sop.core.impl.repository.BORepository;

@Repository
@RequiredArgsConstructor
public class BORepositoryImpl implements BORepository {
    private final DSLContext dsl;

    @Override
    public BO create(BO businessObject) {
        return null;
    }

    @Override
    public BO update(BO bo) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public BO getById(UUID id) {
        return null;
    }
}
