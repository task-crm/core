package ru.sop.core.api.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntityFieldConstants {
    public static final String FIELD_NAME_REGEX = "^[a-zA-Z0-9.$:^_]+";
}
