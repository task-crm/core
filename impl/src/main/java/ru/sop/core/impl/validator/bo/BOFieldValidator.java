package ru.sop.core.impl.validator.bo;

import ru.sop.core.impl.validator.ValidationResult;
import ru.sop.core.impl.validator.Validator;

@FunctionalInterface
public interface BOFieldValidator<V> extends Validator<ValidationResult, V> {
}
