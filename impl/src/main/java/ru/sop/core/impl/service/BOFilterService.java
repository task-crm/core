package ru.sop.core.impl.service;

import ru.sop.core.impl.model.BO;
import ru.sop.core.impl.model.cmd.BOCommand;

public interface BOFilterService {

    /**
     * Фильтрует поля бизнес объекта
     * При фильтрации удаляются поля, которые: не существуют, только для чтения, архивные и т.д.
     *
     * @param cmd объект содержащий бизнес объект и мета данные
     * @return отфильтрованные бизнес объект
     */
    BO filter(BOCommand cmd);
}
