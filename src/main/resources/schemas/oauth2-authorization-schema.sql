CREATE TABLE IF NOT EXISTS oauth2_authorization
(
    id                            varchar(100) NOT NULL,
    registered_client_id          varchar(100) NOT NULL,
    principal_name                varchar(200) NOT NULL,
    authorization_grant_type      varchar(100) NOT NULL,
    attributes                    blob          DEFAULT NULL,
    state                         varchar(500)  DEFAULT NULL,
    authorization_code_value      blob DEFAULT NULL,
    authorization_code_issued_at  timestamp      NULL,
    authorization_code_expires_at timestamp      NULL,
    authorization_code_metadata   blob          DEFAULT NULL,
    access_token_value            blob          DEFAULT NULL,
    access_token_issued_at        timestamp      NULL,
    access_token_expires_at       timestamp      NULL,
    access_token_metadata         blob          DEFAULT NULL,
    access_token_type             varchar(100)  DEFAULT NULL,
    access_token_scopes           varchar(1000) DEFAULT NULL,
    oidc_id_token_value           blob          DEFAULT NULL,
    oidc_id_token_issued_at       timestamp      NULL,
    oidc_id_token_expires_at      timestamp      NULL,
    oidc_id_token_metadata        blob          DEFAULT NULL,
    refresh_token_value           blob          DEFAULT NULL,
    refresh_token_issued_at       timestamp      NULL,
    refresh_token_expires_at      timestamp      NULL,
    refresh_token_metadata        blob          DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS oauth2_authorization_consent
(
    registered_client_id varchar(100)  NOT NULL,
    principal_name       varchar(200)  NOT NULL,
    authorities          varchar(1000) NOT NULL,
    PRIMARY KEY (registered_client_id, principal_name)
);

CREATE TABLE IF NOT EXISTS resource_owner_roles (
      id       int  NOT NULL,
      name     varchar(100) NOT NULL,
      PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS workspace (
    workspace_id int NOT NULL,
    workspace_name varchar(1000) NOT NULL,
    workspace_owner_id int(100) REFERENCES resource_owner(resource_owner_id),
    PRIMARY KEY (workspace_id)
);

CREATE TABLE IF NOT EXISTS resource_owner (
    resource_owner_id int           NOT NULL,
    username          varchar(1000) NOT NULL,
    hashedPassword    varchar(1000) NOT NULL,
    enabled           BIT           default 0,
    locked            BIT           default 0,
    deleted           BIT           default 0,
    workspace_id      int           NOT NULL,
    FOREIGN KEY(workspace_id) REFERENCES workspace(workspace_id),
    PRIMARY KEY(resource_owner_id)
);

CREATE TABLE IF NOT EXISTS resource_owner_roles_mapping (
    resource_owner_id int  NOT NULL,
    role_id           int  NOT NULL,
    FOREIGN KEY(resource_owner_id) REFERENCES resource_owner(resource_owner_id),
    FOREIGN KEY(role_id) REFERENCES resource_owner_roles(id),
    UNIQUE(role_id, resource_owner_id)
);

CREATE TABLE IF NOT EXISTS oauth2_registered_client
(
    id                            varchar(100)                            NOT NULL,
    client_id                     varchar(100)                            NOT NULL,
    client_id_issued_at           timestamp     DEFAULT CURRENT_TIMESTAMP NOT NULL,
    client_secret                 varchar(200)  DEFAULT NULL,
    client_secret_expires_at      timestamp NULL,
    client_name                   varchar(200)                            NOT NULL,
    client_authentication_methods varchar(1000)                           NOT NULL,
    authorization_grant_types     varchar(1000)                           NOT NULL,
    redirect_uris                 varchar(1000) DEFAULT NULL,
    scopes                        varchar(1000)                           NOT NULL,
    client_settings               varchar(2000)                           NOT NULL,
    token_settings                varchar(2000)                           NOT NULL,
    client_owner_id               int(100)                             NOT NULL,
    FOREIGN KEY(client_owner_id)  REFERENCES resource_owner(resource_owner_id),
    PRIMARY KEY (id)
);


INSERT INTO workspace(workspace_id, workspace_name, workspace_owner_id) VALUES( 1, "master", 1);

INSERT INTO resource_owner(resource_owner_id, username, hashedPassword, enabled, locked, deleted, workspace_id)
    VALUES(1, "admin", "admin", true, false, false, 1);


INSERT INTO resource_owner_roles(id, name) VALUES(1, "APP_ADMIN");

INSERT INTO resource_owner_roles_mapping(resource_owner_id, role_id) VALUES(1, 1);
