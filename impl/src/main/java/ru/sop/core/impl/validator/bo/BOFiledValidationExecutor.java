package ru.sop.core.impl.validator.bo;

import java.util.Set;
import ru.sop.core.impl.model.BO;
import ru.sop.core.impl.validator.ValidationExecutor;
import ru.sop.core.impl.validator.ValidationResult;

@FunctionalInterface
public interface BOFiledValidationExecutor extends ValidationExecutor<Set<ValidationResult>, BO> {
}
