package com.tenax.servico.controller;

import org.testcontainers.containers.PostgreSQLContainer;

public abstract class AbstractContainerBase {
    static protected PostgreSQLContainer POSTGRES_SQL_CONTAINER = null;
    static {
        POSTGRES_SQL_CONTAINER = new PostgreSQLContainer();
        POSTGRES_SQL_CONTAINER.start();
        System.setProperty("spring.datasource.url",POSTGRES_SQL_CONTAINER.getJdbcUrl());
        System.setProperty("spring.datasource.username",POSTGRES_SQL_CONTAINER.getUsername());
        System.setProperty("spring.datasource.password",POSTGRES_SQL_CONTAINER.getPassword());
        System.setProperty("spring.jpa.hibernate.ddl-auto","update");
    }
}
