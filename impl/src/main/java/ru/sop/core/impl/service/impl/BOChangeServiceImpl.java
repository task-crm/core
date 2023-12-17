package ru.sop.core.impl.service.impl;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sop.core.impl.enums.OutBoxType;
import ru.sop.core.impl.model.Audit;
import ru.sop.core.impl.model.BO;
import ru.sop.core.impl.model.EntityField;
import ru.sop.core.impl.model.cmd.BOCreateCmd;
import ru.sop.core.impl.model.cmd.BOUpdateCmd;
import ru.sop.core.impl.repository.BORepository;
import ru.sop.core.impl.service.BOChangeService;
import ru.sop.core.impl.service.BoValidationService;
import ru.sop.core.impl.service.EntityFieldService;
import ru.sop.core.impl.service.EntityService;
import ru.sop.core.impl.service.OutBoxService;

@Slf4j
@Service
@RequiredArgsConstructor
public class BOChangeServiceImpl implements BOChangeService {
    private final EntityService entityService;
    private final EntityFieldService entityFieldService;
    private final BoValidationService boValidationService;
    private final OutBoxService outBoxService;
    private final BORepository boRepository;

    @Override
    //@Transaction
    public BO create(BOCreateCmd cmd) {
        var entity = entityService.getById(cmd.entityId());
        var fieldsByName = entityFieldService.getFieldsByNameByEntityId(entity.id());
        var boAfterFilter = filterBo(fieldsByName, cmd.bo());
        boValidationService.validate(fieldsByName, boAfterFilter);
        var bo = enrichBeforeCreate(boAfterFilter);
        outBoxService.create(bo, OutBoxType.BO);
        return boRepository.create(bo);
    }

    //@Transaction
    @Override
    public BO patch(BOUpdateCmd cmd) {
        var entity = entityService.getById(cmd.entityId());
        var fields = entityFieldService.getFieldsByNameByEntityId(entity.id());
        var boAfterFilter = filterBo(fields, cmd.bo());
        boValidationService.validate(fields, boAfterFilter);
        var bo = enrichBeforeUpdate(boAfterFilter);
        return boRepository.update(bo);
    }

    private static BO filterBo(Map<String, EntityField> fieldsByName, BO bo) {
        var data = filterData(fieldsByName, bo.getData());
        var references = filterReferences(fieldsByName, bo.getReferences());
        return bo.toBuilder()
            .data(data)
            .references(references)
            .build();
    }

    private static Map<String, Object> filterData(Map<String, EntityField> fieldsByName, Map<String, Object> data) {
        return data.entrySet().stream()
            .filter(entry -> isCanSave(fieldsByName.get(entry.getKey())))
            .collect(HashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), Map::putAll);
    }

    private static boolean isCanSave(EntityField entityField) {
        return !entityField.archived()
            || !entityField.readOnly();
    }

    private static Map<String, Object> filterReferences(Map<String, EntityField> fieldsByName,
                                                        Map<String, Object> references) {
        return references.entrySet().stream()
            .filter(entry -> isCanSave(fieldsByName.get(entry.getKey())))
            .collect(HashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), Map::putAll);
    }

    private static BO enrichBeforeCreate(BO bo) {
        return bo.toBuilder()
            .id(bo.getId() == null ? UUID.randomUUID() : bo.getId())
            .audit(getAuditForCreate())
            .build();

    }

    private static Audit getAuditForCreate() {
        var now = OffsetDateTime.now();
        return Audit.builder()
            .createdBy(UUID.randomUUID())
            .updatedBy(UUID.randomUUID())
            .createdDate(now)
            .updateDate(now)
            .build();
    }

    private static BO enrichBeforeUpdate(BO bo) {
        return bo.toBuilder()
            .audit(getAuditForUpdate(bo.getAudit()))
            .build();
    }

    private static Audit getAuditForUpdate(Audit audit) {
        var now = OffsetDateTime.now();
        return audit.toBuilder()
            .updatedBy(UUID.randomUUID())
            .updateDate(now)
            .build();
    }
}
