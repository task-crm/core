package ru.sop.core.impl.validator.bo;

import ru.bestclick.exceptionlib.validator.ValidationResult;
import ru.bestclick.exceptionlib.validator.Validator;

@FunctionalInterface
public interface BOFieldValidator<V> extends Validator<ValidationResult, V> {
}
