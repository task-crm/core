package ru.sop.core.impl.helper;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.bestclick.exceptionlib.config.ThreadLocalStorage;
import ru.sop.core.impl.model.Audit;

@Component
@RequiredArgsConstructor
public class AuditHelper {
    private final ClockHelper clockHelper;

    public Audit getForCreate() {
        val now = clockHelper.now();
        return Audit.builder()
            .createdBy(ThreadLocalStorage.getUserId())
            .updatedBy(ThreadLocalStorage.getUserId())
            .createdDate(now)
            .updatedDate(now)
            .build();
    }

    public Audit getForUpdate(Audit audit) {
        val now = clockHelper.now();
        return audit.toBuilder()
            .updatedBy(ThreadLocalStorage.getUserId())
            .updatedDate(now)
            .build();
    }
}
