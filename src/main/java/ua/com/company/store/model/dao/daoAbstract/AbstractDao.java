package ua.com.company.store.model.dao.daoAbstract;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.ConnectionPool;
import org.apache.tomcat.jdbc.pool.DataSource;
import ua.com.company.store.model.dao.connection.ConnectionPoolDataSource;
import ua.com.company.store.model.dao.connection.JDBCConnectionPool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * Created by Владислав on 22.1 1.2017.
 */
public abstract class AbstractDao<T> implements GenericDAO<T> {
    private Logger logger = Logger.getRootLogger();
    private ConnectionPoolDataSource connectionPoolDataSource;
    private JDBCConnectionPool jdbcConnectionPool;


    public AbstractDao(JDBCConnectionPool jdbcConnectionPool) {
        this.jdbcConnectionPool = jdbcConnectionPool;
        logger.info("Created dao layer + " +  this.toString());
    }

    /**
     * @return returns sql statement as string for getting all elements 'SELECT * FROM [TABLE]'
     */
    public abstract String getSelectQuery();

    /**
     * @return returns sql statement as string for insert new note 'insert into [table] (column ...) values (?...)'
     */
    public abstract String getCreateQuery();

    /**
     * @return returns sql statement as string for updating elements 'update [table] set [column = ? ..,.] where id =?'
     */
    public abstract String getUpdateQuery();

    /**
     * @return returns sql statement as string for deleting rows'SELECT * FROM [TABLE]'
     */
    public abstract String getDeleteQuery();


    /**
     * @return returns sql statement as string for insert rows
     */
    public abstract String getInsertQuery();

    protected abstract List<T> parseResultSet(ResultSet rs);

    protected abstract void prepareStatemantForInsert(PreparedStatement statement, T object);


    protected abstract void prepareStatemantForDelete(PreparedStatement statement, T object);

    @Override
    public void insert(T object) {
        String query = getInsertQuery();
        query += " VALUES (?,?,?,?,?,?)" ;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = getConnectionFromPool();
            preparedStatement = connection.prepareStatement(query);
            prepareStatemantForInsert(preparedStatement, object);
            preparedStatement.executeUpdate();
            logger.info("Insert into db new object " + object.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("error in inserting of object" + e);
        }
        finally {
            closeResources(preparedStatement,connection);
        }
    }

    ;

    @Override
    public T getById(int key) {
        List<T> list = null;
        String sql_query = getSelectQuery();
        sql_query += " where id = ? ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnectionFromPool();
            preparedStatement = connection.prepareStatement(sql_query);
            preparedStatement.setInt(1, key);
            resultSet = preparedStatement.executeQuery();
            list = parseResultSet(resultSet);

            if (list == null) return null;
            if (list.size() > 1) return null;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Cant search admin by Id " + key);
        }
        finally {
            closeResources(resultSet,preparedStatement,connection);
        }
        return list.iterator().next();
    }

    public List<T> getAll() {
        List<T> list = null;
        String sql = getSelectQuery();
        Connection connection= null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnectionFromPool();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            list = parseResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Cant get all admins " + e);
        }
        finally {
            closeResources(resultSet,preparedStatement,connection);
        }
        return list;
    }

    /**
     * Method which persist object
     *
     * @param object
     * @return
     */
    public void delete(T object) {
        Connection connection = null;
        try {
            connection = getConnectionFromPool();
            String sql = getDeleteQuery();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected Connection getConnectionFromPool() throws SQLException{
        return jdbcConnectionPool.getConnection();

       }

    protected void closeResources(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection){
        try {
            if(resultSet != null){
                resultSet.close();

                logger.info("Closed resource --" +resultSet.toString() );
            }
            if (preparedStatement != null) {
                preparedStatement.close();

                logger.info("Closed  resource --" +preparedStatement.toString() );
            }
            if (connection !=null) {
                connection.close();

                logger.info("Closed resource --" +connection.toString() );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void closeResources(PreparedStatement preparedStatement, Connection connection){
        try {
            if (preparedStatement !=null) {
                preparedStatement.close();
                logger.info("Closed resource --" +preparedStatement.toString() );
            }
            if (connection != null) {
                connection.close();

                logger.info("Closed resource --" +connection.toString() );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
