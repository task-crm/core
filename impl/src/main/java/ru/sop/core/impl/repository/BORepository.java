package ru.sop.core.impl.repository;

import java.util.UUID;
import org.checkerframework.checker.nullness.qual.Nullable;
import ru.sop.core.impl.model.bo.BO;
import ru.sop.core.impl.model.page.Page;
import ru.sop.core.impl.model.page.PageSelector;

public interface BORepository {

    Page<BO> getPage(PageSelector selector);

    @Nullable
    BO findOne(UUID id);

    BO getOne(UUID id);

    void create(BO businessObject);

    void update(BO bo);

    void deleteOne(UUID id);
}
