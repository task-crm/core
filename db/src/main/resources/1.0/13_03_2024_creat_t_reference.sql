--liquibase formatted sql
--changeset JavaBiteCode:13_03_2024_creat_t_reference
CREATE TABLE t_reference
(
    tenant_id    UUID,
    source       UUID                     NOT NULL,
    destination  UUID,
    type         varchar(50)              NOT NULL,

    created_date TIMESTAMP WITH TIME ZONE NOT NULL,
    update_date  TIMESTAMP WITH TIME ZONE NOT NULL,
    create_by    UUID                     NOT NULL,
    updated_by   UUID                     NOT NULL,

    Foreign key (source, tenant_id) references t_business_object (id, tenant_id) on delete cascade,
    Foreign key (destination, tenant_id) references t_business_object (id, tenant_id) on delete cascade,
    PRIMARY KEY (source, destination)
);

--changeset JavaBiteCode:13_03_2024_creat_t_reference_comment runOnChange:true
COMMENT ON TABLE t_reference IS 'Таблица содержит информацию о ссылках графа бизнес объектов';
COMMENT
    ON COLUMN t_reference.tenant_id IS 'идентификатор организации';
COMMENT
    ON COLUMN t_reference.source IS 'идентификатор ресурса';
COMMENT
    ON COLUMN t_reference.destination IS 'идентификатор назначения';
COMMENT
    ON COLUMN t_reference.type IS 'тип связи';

--audit
COMMENT
    ON COLUMN t_reference.created_date IS 'дата создания';
COMMENT
    ON COLUMN t_reference.update_date IS 'дата обновления';
COMMENT
    ON COLUMN t_reference.create_by IS 'идентификатор создателя';
COMMENT
    ON COLUMN t_reference.updated_by IS 'идентификатор обновившего';
