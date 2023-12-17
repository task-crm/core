package ru.sop.core.impl.validator;

@FunctionalInterface
public interface Validator<R extends ValidationResult, V> {

    R validate(V value);
}
