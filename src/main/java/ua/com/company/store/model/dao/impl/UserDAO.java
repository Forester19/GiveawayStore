package ua.com.company.store.model.dao.impl;

import org.apache.log4j.Logger;
import ua.com.company.store.model.dao.connection.JDBCConnectionPool;
import ua.com.company.store.model.dao.daoAbstract.AbstractDao;
import ua.com.company.store.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Владислав on 22.11.2017.
 */
public class UserDAO extends AbstractDao<User> {
    private Logger logger = Logger.getRootLogger();
    public UserDAO(JDBCConnectionPool jdbcConnectionPool) {

        super(jdbcConnectionPool);
    }

    @Override
    public User update() {
        return null;
    }

    @Override
    public String getSelectQuery() {
        return "select * from onlinestoreproject.users ";
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
        return "insert into onlinestoreproject.users(id,nickname,password,email,role,is_defaulter) VALUES (?,?,?,?,?,?)";
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
        try {
            statement.setInt(1,object.getId());
            statement.setString(2,object.getNickname());
            statement.setString(3,object.getPassword());
            statement.setString(4,object.getEmail());
            statement.setBoolean(5,object.isRole());
            statement.setBoolean(6,object.isDefaulter());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatemantForDelete(PreparedStatement statement, User object) {

    }

    @Override
    public User getByParameter(String nickname) {
       List<User> list = null;
        String query = getSelectQuery() + "where nickname = ?";
        Connection connection= null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnectionFromPool();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,nickname);
            resultSet = preparedStatement.executeQuery();
            list = parseResultSet(resultSet);
            if (list ==null || list.size()>1){
                logger.error("Cant search users with nickname " + nickname);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Cant get all admins " + e);
        }
        finally {
            closeResources(resultSet,preparedStatement,connection);
        }
        return list.get(0);
    }
}
