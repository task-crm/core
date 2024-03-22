package ru.sop.core.impl.validator.bo.impl;

import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Component;
import ru.bestclick.exceptionlib.validator.ValidationResult;
import ru.bestclick.exceptionlib.validator.impl.ValidationResultDefault;
import ru.sop.core.impl.enums.FieldTypeEnum;
import ru.sop.core.impl.validator.bo.BOFieldValidator;

@Component
public class DurationValidator implements BOFieldValidator {

    @Override
    @Nullable
    public ValidationResult validate(Object duration) {
        if (duration instanceof Duration) {
            return null;
        }

        try {
            Duration.parse(Objects.toString(duration));
        } catch (DateTimeParseException ex) {
            return ValidationResultDefault.builder()
                .message("field.type.duration.not_valid")
                .logMessage("field.type.duration.not_valid")
                .build();
        }
        return null;
    }

    @Override
    public String getType() {
        return FieldTypeEnum.DURATION.getType();
    }
}
