package ru.sop.core.impl.validator.bo.impl;

import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Component;
import ru.bestclick.exceptionlib.validator.ValidationResult;
import ru.bestclick.exceptionlib.validator.impl.ValidationResultDefault;
import ru.sop.core.impl.enums.FieldTypeEnum;
import ru.sop.core.impl.validator.bo.BOFieldValidator;

@Component
public class DateTimeValidator implements BOFieldValidator {

    @Override
    @Nullable
    public ValidationResult validate(Object dateTime) {
        if (dateTime instanceof OffsetDateTime) {
            return null;
        }

        try {
            OffsetDateTime.parse(Objects.toString(dateTime));
        } catch (DateTimeParseException ex) {
            return ValidationResultDefault.builder()
                .message("field.type.date_time.not_valid")
                .logMessage("field.type.date_time.not_valid")
                .build();
        }
        return null;
    }

    @Override
    public String getType() {
        return FieldTypeEnum.DATETIME.getType();
    }
}
