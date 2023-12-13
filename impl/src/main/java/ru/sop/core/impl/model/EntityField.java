package ru.sop.core.impl.model;

import java.util.UUID;
import ru.sop.core.impl.enums.FieldTypeEnum;
/**
 * Поле сущности {@link Entity}. Содержит имя, описание, тип и другие настройки. Это поле по факту является полем
 * бизнес объекта {@link BO};
 */
public record EntityField(
    UUID id,
    String name,
    String description,
    FieldTypeEnum type,
    EntityFieldSettings settings,
    /**
     * Поле создано системой(разработчиком)
     */
    boolean system,
    /**
     * Не отображается на ui, но передается на ui
     */
    boolean hiddenFromUser,
    /**
     * Не отображается на ui, и не передается на ui
     */
    boolean hiddenFromUi,
    /**
     * Поле заархивировано
     */
    boolean archived,
    boolean readOnly,
    Audit audit
) {
}
