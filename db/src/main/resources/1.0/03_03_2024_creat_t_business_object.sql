--liquibase formatted sql
--changeset JavaBiteCode:03_03_2024_creat_t_business_object
CREATE TABLE t_business_object
(
    id           UUID,
    tenant_id    UUID,
    entity_id    UUID                     NOT NULL,
    name         VARCHAR(128)             NOT NULL,
    data         JSONB,
    system       BOOLEAN                  NOT NULL,

    created_date TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_date TIMESTAMP WITH TIME ZONE NOT NULL,
    created_by   UUID                     NOT NULL,
    updated_by   UUID                     NOT NULL,

    PRIMARY KEY (id, tenant_id)
);

--changeset JavaBiteCode:03_03_2024_creat_t_business_object_comment runOnChange:true
COMMENT ON TABLE t_business_object IS 'Таблица содержит информацию о бизнес объектах организаций';
COMMENT
    ON COLUMN t_business_object.id IS 'идентификатор';
COMMENT
    ON COLUMN t_business_object.tenant_id IS 'идентификатор организации';
COMMENT
    ON COLUMN t_business_object.entity_id IS 'идентификатор сущности к которой принадлежит объект';
COMMENT
    ON COLUMN t_business_object.name IS 'название объекта';
COMMENT
    ON COLUMN t_business_object.data IS 'информация об объекте';
COMMENT
    ON COLUMN t_business_object.system IS 'признак системности. Oбъект создан системой для её обслуживания. Не может быть удален';

--audit
COMMENT
    ON COLUMN t_business_object.created_date IS 'дата создания';
COMMENT
    ON COLUMN t_business_object.updated_date IS 'дата обновления';
COMMENT
    ON COLUMN t_business_object.created_by IS 'идентификатор создателя';
COMMENT
    ON COLUMN t_business_object.updated_by IS 'идентификатор обновившего';
