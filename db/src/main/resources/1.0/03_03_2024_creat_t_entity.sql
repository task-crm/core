--liquibase formatted sql
--changeset JavaBiteCode:03_03_2024_creat_t_entity
CREATE TABLE t_entity
(
    id           UUID,
    tenant_id    UUID,
    name         VARCHAR(128)             NOT NULL,
    label        VARCHAR(128)             NOT NULL,
    description  VARCHAR(128),
    icon         VARCHAR(128)             NOT NULL,
    color        VARCHAR(12)              NOT NULL,
    settings     JSONB,
    system       BOOLEAN                  NOT NULL,
    archived     BOOLEAN                  NOT NULL,

    created_date TIMESTAMP WITH TIME ZONE NOT NULL,
    update_date  TIMESTAMP WITH TIME ZONE NOT NULL,
    create_by    UUID                     NOT NULL,
    updated_by   UUID                     NOT NULL,

    PRIMARY KEY (id, tenant_id),
    CONSTRAINT unique_entity_name_per_tenant unique (tenant_id, name)
);

--changeset JavaBiteCode:03_03_2024_creat_t_entity_comment runOnChange:true
COMMENT ON TABLE t_entity IS 'Таблица содержит информацию о сущностях бизнес объектов';
COMMENT
    ON COLUMN t_entity.id IS 'идентификатор';
COMMENT
    ON COLUMN t_entity.tenant_id IS 'идентификатор организации';
COMMENT
    ON COLUMN t_entity.name IS 'наименование сущности';
COMMENT
    ON COLUMN t_entity.label IS 'лейбл отображаемый пользователю как имя';
COMMENT
    ON COLUMN t_entity.description IS 'описание сущности';
COMMENT
    ON COLUMN t_entity.icon IS 'иконка для отображения';
COMMENT
    ON COLUMN t_entity.color IS 'цвет для отображения';
COMMENT
    ON COLUMN t_entity.settings IS 'настройки сущности';
COMMENT
    ON COLUMN t_entity.system IS 'сущность создана системой для её обслуживания. Не может быть удалена/архивирована';
COMMENT
    ON COLUMN t_entity.archived IS 'сущность архивирована';

--audit
COMMENT
    ON COLUMN t_entity.created_date IS 'дата создания';
COMMENT
    ON COLUMN t_entity.update_date IS 'дата обновления';
COMMENT
    ON COLUMN t_entity.create_by IS 'идентификатор создателя';
COMMENT
    ON COLUMN t_entity.updated_by IS 'идентификатор обновившего';
