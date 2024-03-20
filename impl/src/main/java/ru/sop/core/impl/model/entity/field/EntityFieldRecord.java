package ru.sop.core.impl.model.entity.field;

import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.sop.core.impl.enums.FieldTypeEnum;
import ru.sop.core.impl.model.bo.BO;
import ru.sop.core.impl.model.entity.Entity;

/**
 * Поле сущности {@link Entity}. Содержит имя, описание, тип и другие настройки. Это поле по факту является полем
 * бизнес объекта {@link BO};
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class EntityFieldRecord {
    private UUID id;
    private UUID entityId;
    private String name;
    private String label;
    private String description;
    private FieldTypeEnum type;
    private EntityFieldSettings settings;
    /**
     * Поле сущности создано системой для её обслуживания. Не может быть удалено/архивировано
     */
    private boolean system;
    /**
     * Не отображается на ui, но передается на ui
     */
    private boolean hiddenFromUser;
    /**
     * Не отображается на ui, и не передается на ui
     */
    private boolean hiddenFromUi;
    /**
     * Поле архивировано пользователем. Такие Поля исключаются из работы как будто их не существует, но должны
     * поддерживаться в актуальном состоянии (например, их должны обрабатывать миграции), так как пользователь может
     * вернуть Поле из архива.
     */
    private boolean archived;
    /**
     * Поле только для чтения. Его невозможно изменить
     */
    private boolean readOnly;
    /**
     * Поле обязательно должно быть инициализировано. Без этого поля не может существовать БО
     */
    private boolean required;

    private OffsetDateTime createdDate;
    private UUID createdBy;
    private OffsetDateTime updatedDate;
    private UUID updatedBy;
}
