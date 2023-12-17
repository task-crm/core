package ru.sop.core.impl.validator;

import java.util.Set;

@FunctionalInterface
public interface ValidationExecutor <R extends Set<ValidationResult>, V>{

    R execute(V value);
}
