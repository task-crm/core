package ru.sop.core.impl.model;

import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.sop.core.impl.enums.FieldTypeEnum;

/**
 * Поле сущности {@link Entity}. Содержит имя, описание, тип и другие настройки. Это поле по факту является полем
 * бизнес объекта {@link BO};
 */
@Value
@Builder(toBuilder = true)
@EqualsAndHashCode(of = {"id"})
public class EntityField {
    UUID id;
    String name;
    String description;
    FieldTypeEnum type;
    EntityFieldSettings settings;
    /**
     * Поле создано системой(разработчиком)
     */
    boolean system;
    /**
     * Не отображается на ui, но передается на ui
     */
    boolean hiddenFromUser;
    /**
     * Не отображается на ui, и не передается на ui
     */
    boolean hiddenFromUi;
    /**
     * Поле архивировано пользователем. Такие Поля исключаются из работы как будто их не существует, но должны
     * поддерживаться в актуальном состоянии (например, их должны обрабатывать миграции), так как пользователь может
     * вернуть Поле из архива.
     */
    boolean archived;
    /**
     * Поле только для чтения. Его невозможно изменить
     */
    boolean readOnly;
    /**
     * Поле обязательно должно быть инициализировано. Без этого поля не может существовать БО
     */
    boolean required;

    Audit audit;
}
