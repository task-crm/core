package ru.sop.core.impl.transitionallayer.impl;

import static core.tables.TEntityField.T_ENTITY_FIELD;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.commons.collections4.CollectionUtils;
import org.jooq.Condition;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Component;
import ru.sop.core.impl.jooq.SpecificationBuilder;
import ru.sop.core.impl.model.data.Filter;
import ru.sop.core.impl.model.data.Page;
import ru.sop.core.impl.model.entity.field.EntityField;
import ru.sop.core.impl.model.entity.field.EntityFieldGetCmd;
import ru.sop.core.impl.repository.EntityFieldRepository;
import ru.sop.core.impl.transitionallayer.EntityFieldTransitionalLayer;

@Component
@RequiredArgsConstructor
public class EntityFieldJooqTransitionalLayerImpl implements EntityFieldTransitionalLayer {
    private final EntityFieldRepository entityFieldRepository;
    private final ObjectMapper objectMapper;

    @Override
    public Page<EntityField> getPage(EntityFieldGetCmd cmd) {
        val condition = getCondition(cmd.getData().getFilters());
        return entityFieldRepository.getPage(condition);
    }

    private Condition getCondition(Set<Filter> filters) {
        if (CollectionUtils.isEmpty(filters)) {
            return DSL.trueCondition();
        }
        return new SpecificationBuilder(T_ENTITY_FIELD, List.of(), objectMapper)
            .build(filters);
    }
}
