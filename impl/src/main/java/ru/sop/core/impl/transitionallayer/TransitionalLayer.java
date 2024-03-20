package ru.sop.core.impl.transitionallayer;

/**
 * Содержит специфичную логику для всех библиотек отображения баз данных(например Jooq, Spring Data JPA).
 * Этот уровень необходим для отделения логики связанной с библиотекой от бизнес-логики.
 * Бизнес-логика не должна содержать например такие классы как: CriteriaBuilder(hibernate), Condition(Jooq) и другие.
 * Для создания запросов в переходные слои, используйте классы из пакета : {@link ru.sop.core.impl.model.data}
 */
public interface TransitionalLayer {
}
