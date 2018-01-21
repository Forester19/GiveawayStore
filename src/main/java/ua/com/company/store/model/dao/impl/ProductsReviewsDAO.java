package ua.com.company.store.model.dao.impl;

import ua.com.company.store.model.dao.connection.JDBCConnectionPool;
import ua.com.company.store.model.dao.daoAbstract.AbstractDao;
import ua.com.company.store.model.entity.Products_Reviews;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Владислав on 21.01.2018.
 */
public class ProductsReviewsDAO extends AbstractDao<Products_Reviews> {

    public ProductsReviewsDAO(JDBCConnectionPool jdbcConnectionPool) {
        super(jdbcConnectionPool);
    }

    @Override
    public Products_Reviews update() {
        return null;
    }

    @Override
    public String getSelectQuery() {
        return null;
    }

    @Override
    public String getCreateQuery() {
        return null;
    }

    @Override
    public String getUpdateQuery() {
        return null;
    }

    @Override
    public String getDeleteQuery() {
        return null;
    }

    @Override
    public String getInsertQuery() {
        return null;
    }

    @Override
    protected List<Products_Reviews> parseResultSet(ResultSet rs) {
        return null;
    }

    @Override
    protected void prepareStatemantForInsert(PreparedStatement statement, Products_Reviews object) {

    }

    @Override
    protected void prepareStatemantForDelete(PreparedStatement statement, Products_Reviews object) {

    }

    @Override
    public Products_Reviews getByParameter(String parameter) {
        return null;
    }
}
