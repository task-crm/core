--liquibase formatted sql
--changeset JavaBiteCode:init
CREATE TABLE t_tenant
(
    id           UUID PRIMARY KEY,
    name         VARCHAR(150),

    created_date TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_date TIMESTAMP WITH TIME ZONE NOT NULL,
    created_by   UUID                     NOT NULL,
    updated_by   UUID                     NOT NULL
);

--changeset JavaBiteCode:init_comment runOnChange:true
COMMENT
ON TABLE t_tenant IS 'Таблица содержит организации';
COMMENT
ON COLUMN t_tenant.id IS 'идентификатор';
    COMMENT
ON COLUMN t_tenant.name IS 'название организации';

--audit
COMMENT
ON COLUMN t_tenant.created_date IS 'дата создания';
COMMENT
ON COLUMN t_tenant.updated_date IS 'дата обновления';
COMMENT
ON COLUMN t_tenant.created_by IS 'идентификатор создателя';
COMMENT
ON COLUMN t_tenant.updated_by IS 'идентификатор обновившего';
