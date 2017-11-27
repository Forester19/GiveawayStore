package ua.com.company.store.model.dao.impl;

import ua.com.company.store.model.dao.daoAbstract.AbstractDao;
import ua.com.company.store.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Владислав on 22.11.2017.
 */
public class UserDAO extends AbstractDao<User> {
    @Override
    public User update() {
        return null;
    }

    @Override
    public String getSelectQuery() {
        return "select * from onlinestoreproject.users";
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
    protected List<User> parseResultSet(ResultSet rs) {
        List<User> list = new ArrayList<>();
        try {
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setNickname(rs.getString("nickname"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getBoolean("role"));
                user.setDefaulter(rs.getBoolean("is_defaulter"));
                list.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected void prepareStatemantForInsert(PreparedStatement statement, User object) {

    }

    @Override
    protected void prepareStatemantForDelete(PreparedStatement statement, User object) {

    }
}
