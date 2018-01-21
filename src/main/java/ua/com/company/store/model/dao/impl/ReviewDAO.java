package ua.com.company.store.model.dao.impl;

import ua.com.company.store.model.dao.connection.JDBCConnectionPool;
import ua.com.company.store.model.dao.daoAbstract.AbstractDao;
import ua.com.company.store.model.entity.Review;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Владислав on 21.01.2018.
 */
public class ReviewDAO extends AbstractDao<Review> {

    public ReviewDAO(JDBCConnectionPool jdbcConnectionPool) {
        super(jdbcConnectionPool);
    }

    @Override
    public Review update() {
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
    protected List<Review> parseResultSet(ResultSet rs) {
        return null;
    }

    @Override
    protected void prepareStatemantForInsert(PreparedStatement statement, Review object) {

    }

    @Override
    protected void prepareStatemantForDelete(PreparedStatement statement, Review object) {

    }

    @Override
    public Review getByParameter(String parameter) {
        return null;
    }
}
