package ru.sop.core.impl.jooq;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JooqConstants {
    public static final String BASE_ALIAS = "base";
    public static final String TENANT_ID_FIELD = "tenant_id";
}
