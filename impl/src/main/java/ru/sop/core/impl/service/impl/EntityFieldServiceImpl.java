package ru.sop.core.impl.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.bestclick.exceptionlib.exception.BusinessException;
import ru.sop.core.api.dto.page.Operation;
import ru.sop.core.impl.helper.AuditHelper;
import ru.sop.core.impl.metadata.Metadata;
import ru.sop.core.impl.model.entity.field.EntityField;
import ru.sop.core.impl.model.entity.field.EntityFieldGetCmd;
import ru.sop.core.impl.model.page.Filter;
import ru.sop.core.impl.model.page.Page;
import ru.sop.core.impl.model.page.PageSelector;
import ru.sop.core.impl.model.page.Paging;
import ru.sop.core.impl.repository.EntityFieldRepository;
import ru.sop.core.impl.service.EntityFieldService;

@Service
@RequiredArgsConstructor
public class EntityFieldServiceImpl implements EntityFieldService {
    private final EntityFieldRepository entityFieldRepository;
    private final AuditHelper auditHelper;

    @Override
    public Map<String, EntityField> getFieldsByNameByEntityId(UUID entityId) {
        val pageSelector = addFilterWithEntityId(PageSelector.EMPTY, entityId)
            .paging(Paging.UNPAGED)
            .build();
        val fields = entityFieldRepository.getPage(pageSelector).getData();
        return fields.stream()
            .collect(Collectors.toMap(EntityField::getName, Function.identity()));
    }

    @Override
    public EntityField getOne(UUID entityId, UUID fieldId) {
        return entityFieldRepository.getOne(fieldId);
    }

    @Override
    public Page<EntityField> getPage(EntityFieldGetCmd cmd) {
        val pageSelector = addFilterWithEntityId(cmd.getPageSelector(), cmd.getEntityId()).build();
        return entityFieldRepository.getPage(pageSelector);
    }

    @Override
    public EntityField create(Metadata metadata, EntityField field) {
        check(metadata, field);
        val richField = enrichBeforeCreate(field);
        entityFieldRepository.create(richField);
        return richField;
    }

    @Override
    public EntityField update(Metadata metadata, EntityField field) {
        check(metadata, field);
        val richField = enrichBeforeUpdate(field);
        entityFieldRepository.update(richField);
        return richField;
    }

    @Override
    public void deleteOne(UUID entityId, UUID fieldId) {
        entityFieldRepository.deleteOne(fieldId);
    }

    private PageSelector.PageSelectorBuilder addFilterWithEntityId(PageSelector pageSelector, UUID entityId) {
        val filters = new HashSet<>(pageSelector.getFilters());
        filters.add(getEntityIdFilter(entityId));
        return pageSelector.toBuilder()
            .filters(filters);
    }

    private Filter getEntityIdFilter(UUID entityId) {
        return Filter.builder()
            .field("entity_id")
            .operation(Operation.EQUAL)
            .values(List.of(entityId.toString()))
            .build();
    }

    private void check(Metadata metadata, EntityField field) {
        val entity = metadata.findEntityById(field.getEntityId());
        if (entity == null) {
            throw new BusinessException("entity.not_found");
        }
    }

    private EntityField enrichBeforeCreate(EntityField field) {
        return field.toBuilder()
            .id(field.getId() == null ? UUID.randomUUID() : field.getId())
            .audit(auditHelper.getForCreate())
            .build();
    }

    private EntityField enrichBeforeUpdate(EntityField field) {
        return field.toBuilder()
            .audit(auditHelper.getForUpdate(field.getAudit()))
            .build();
    }
}
