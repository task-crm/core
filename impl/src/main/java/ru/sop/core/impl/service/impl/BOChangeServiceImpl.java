package ru.sop.core.impl.service.impl;

import java.time.OffsetDateTime;
import java.util.List;
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

    /**
     * filter
     * validate
     * enrich
     * create out box
     * create save bo
     */
    @Override
    //@Transaction
    public BO create(BOCreateCmd cmd) {
        var entity = entityService.getById(cmd.entityId());
        var fields = entityFieldService.getAllByEntityId(entity.id());
        var boAfterFilter = filterBo(fields, cmd.bo());
        var bo = enrichBeforeCreate(boAfterFilter);
        boValidationService.validate(bo);
        outBoxService.create(bo, OutBoxType.BO);
        return boRepository.create(bo);
    }

    private static BO filterBo(List<EntityField> fields, BO bo) {
        var data = filterData(bo.getData());
        var references = filterReferences(bo.getReferences());
        return bo.toBuilder()
            .data(data)
            .references(references)
            .build();
    }

    private static Map<String, Object> filterData(Map<String, Object> data) {
        return data;
    }

    private static Map<String, Object> filterReferences(Map<String, Object> references) {
        return references;
    }

    /**
     * filter
     * validate
     * enrich
     * create out box
     * update save bo
     */
    //@Transaction
    @Override
    public BO patch(BOUpdateCmd cmd) {
        var bo = enrichBeforeUpdate(cmd.bo());
        return boRepository.update(cmd.id(), bo);
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
