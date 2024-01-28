package ru.sop.core.impl.phase;

import ru.sop.core.impl.model.BO;
import ru.sop.core.impl.model.cmd.BOCommand;

/**
 * Точка входа в выполнение фаз бизнес объекта. Запускает выполнение всех фаз бизнес объекта. Фазы - {@link BOPhase}.
 * Последовательность фаз {@link BOPhaseOrderRegulator}
 */
public interface BOPhaseExecutor {

    /**
     * Запускает все фазы перед созданием/обновлением бизнес объекта
     */
    BO preSave(BOCommand cmd);

    /**
     * Запускает все фазы после создания/обновления бизнес объекта
     */
    void postSave(BOCommand cmd);

    /**
     * Запускает все фазы перед удалением бизнес объекта
     */
    BO preDelete(BOCommand cmd);

    /**
     * Запускает все фазы после удаления бизнес объекта
     */
    void postDelete(BOCommand cmd);
}
