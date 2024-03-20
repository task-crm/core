package ru.sop.core.impl.transitionallayer;

import ru.sop.core.impl.model.data.Page;
import ru.sop.core.impl.model.entity.field.EntityField;
import ru.sop.core.impl.model.entity.field.EntityFieldGetCmd;

/**
 * @see TransitionalLayer
 */
public interface EntityFieldTransitionalLayer extends TransitionalLayer {

    Page<EntityField> getPage(EntityFieldGetCmd cmd);
}
