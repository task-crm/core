package ru.sop.core.impl.metadata;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sop.core.impl.service.EntityFieldService;
import ru.sop.core.impl.service.EntityService;

@Component
@RequiredArgsConstructor
public class MetadataFactory {
    private final EntityFieldService entityFieldService;
    private final EntityService entityService;

    public Metadata create() {
        return LazyMetadata.builder()
            .entityService(entityService)
            .entityFieldService(entityFieldService)
            .build();
    }
}
