package ua.com.company.store.controller.utils;

import ua.com.company.store.model.dao.connection.JDBCConnectionPool;

/**
 * Created by Владислав on 22.12.2017.
 */
public class JDBCConnectionPoolManager {
    private JDBCConnectionPool jdbcConnectionPool;

    public JDBCConnectionPool getJdbcConnectionPool() {
        return jdbcConnectionPool;
    }

    public void setJdbcConnectionPool(JDBCConnectionPool jdbcConnectionPool) {
        this.jdbcConnectionPool = jdbcConnectionPool;
    }
}
