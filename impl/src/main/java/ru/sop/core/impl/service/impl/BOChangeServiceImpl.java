package ru.sop.core.impl.service.impl;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.sop.core.impl.enums.OutBoxType;
import ru.sop.core.impl.helper.ClockHelper;
import ru.sop.core.impl.model.Audit;
import ru.sop.core.impl.model.BO;
import ru.sop.core.impl.model.cmd.BOCreateCmd;
import ru.sop.core.impl.model.cmd.BOUpdateCmd;
import ru.sop.core.impl.repository.BORepository;
import ru.sop.core.impl.service.BOChangeService;
import ru.sop.core.impl.service.BOFilterService;
import ru.sop.core.impl.service.BOValidationService;
import ru.sop.core.impl.service.OutBoxService;

@Slf4j
@Service
@RequiredArgsConstructor
public class BOChangeServiceImpl implements BOChangeService {
    private final BOValidationService boValidationService;
    private final BOFilterService filterService;
    private final OutBoxService outBoxService;
    private final BORepository boRepository;
    private final ClockHelper clockHelper;

    @Override
    //@Transaction
    public BO create(BOCreateCmd cmd) {
        val cmdAfterFilterBo = cmd.of(filterService.filter(cmd));
        boValidationService.check(cmdAfterFilterBo);
        val richBO = enrichBeforeCreate(cmdAfterFilterBo.getBo());
        outBoxService.create(richBO, OutBoxType.BO);
        return boRepository.create(richBO);
    }

    //@Transaction
    @Override
    public BO patch(BOUpdateCmd cmd) {
        val cmdAfterFilterBo = cmd.of(filterService.filter(cmd));
        boValidationService.check(cmdAfterFilterBo);
        val richBO = enrichBeforeUpdate(cmdAfterFilterBo.getBo());
        outBoxService.create(richBO, OutBoxType.BO);
        return boRepository.update(richBO);
    }

    private BO enrichBeforeCreate(BO bo) {
        return bo.toBuilder()
            .id(bo.getId() == null ? UUID.randomUUID() : bo.getId())
            .audit(getAuditForCreate())
            .build();
    }

    private Audit getAuditForCreate() {
        val now = clockHelper.now();
        return Audit.builder()
            .createdBy(UUID.randomUUID())
            .updatedBy(UUID.randomUUID())
            .createdDate(now)
            .updateDate(now)
            .build();
    }

    private BO enrichBeforeUpdate(BO bo) {
        return bo.toBuilder()
            .audit(getAuditForUpdate(bo.getAudit()))
            .build();
    }

    private Audit getAuditForUpdate(Audit audit) {
        val now = clockHelper.now();
        return audit.toBuilder()
            .updatedBy(UUID.randomUUID())
            .updateDate(now)
            .build();
    }
}
