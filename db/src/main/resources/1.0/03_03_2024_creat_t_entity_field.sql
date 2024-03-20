--liquibase formatted sql
--changeset JavaBiteCode:03_03_2024_creat_t_entity_field
CREATE TABLE t_entity_field
(
    id               UUID,
    tenant_id        UUID,
    entity_id        uuid                     NOT NULL,
    name             VARCHAR(128)             NOT NULL,
    label            VARCHAR(128)             NOT NULL,
    description      VARCHAR(128),
    type             VARCHAR(128)             NOT NULL,
    settings         JSONB,
    system           BOOLEAN                  NOT NULL,
    archived         BOOLEAN                  NOT NULL,
    required         BOOLEAN                  NOT NULL,
    hidden_from_user BOOLEAN                  NOT NULL,
    hidden_from_ui   BOOLEAN                  NOT NULL,
    read_only        BOOLEAN                  NOT NULL,

    created_date     TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_date     TIMESTAMP WITH TIME ZONE NOT NULL,
    created_by       UUID                     NOT NULL,
    updated_by       UUID                     NOT NULL,

    PRIMARY KEY (id, tenant_id),
    CONSTRAINT unique_entity_field_name_per_tenant unique (tenant_id, name)
);

--changeset JavaBiteCode:03_03_2024_creat_t_entity_field_comment runOnChange:true
COMMENT ON TABLE t_entity_field IS 'Таблица содержит информацию о полях сущности';
COMMENT
    ON COLUMN t_entity_field.id IS 'идентификатор';
COMMENT
    ON COLUMN t_entity_field.tenant_id IS 'идентификатор организации';
COMMENT
    ON COLUMN t_entity_field.entity_id IS 'идентификатор сущности, к которой принадлежит поле';
COMMENT
    ON COLUMN t_entity_field.name IS 'наименование поля';
COMMENT
    ON COLUMN t_entity_field.label IS 'лейбл отображаемый пользователю как имя';
COMMENT
    ON COLUMN t_entity_field.description IS 'описание сущности';
COMMENT
    ON COLUMN t_entity_field.type IS 'тип поля(связь или обычное поле)';
COMMENT
    ON COLUMN t_entity_field.settings IS 'настройки поля';
COMMENT
    ON COLUMN t_entity_field.system IS 'поле сущности создано системой для её обслуживания. Не может быть удалено/архивировано';
COMMENT
    ON COLUMN t_entity_field.archived IS 'сущность архивирована';
COMMENT
    ON COLUMN t_entity_field.required IS 'обязательное поле для сущности';
COMMENT
    ON COLUMN t_entity_field.hidden_from_user IS 'Не отображается на ui, но передается на ui';
COMMENT
    ON COLUMN t_entity_field.hidden_from_ui IS 'Не отображается на ui, и не передается на ui';
COMMENT
    ON COLUMN t_entity_field.read_only IS 'только для чтения';

--audit
COMMENT
    ON COLUMN t_entity_field.created_date IS 'дата создания';
COMMENT
    ON COLUMN t_entity_field.updated_date IS 'дата обновления';
COMMENT
    ON COLUMN t_entity_field.created_by IS 'идентификатор создателя';
COMMENT
    ON COLUMN t_entity_field.updated_by IS 'идентификатор обновившего';
