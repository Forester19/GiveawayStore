package ua.com.company.store.model.dao.impl;

import ua.com.company.store.model.dao.connection.JDBCConnectionPool;
import ua.com.company.store.model.dao.daoAbstract.AbstractDao;
import ua.com.company.store.model.entity.Image;
import ua.com.company.store.model.entity.Product;

import java.sql.*;
import java.util.List;

/**
 * Created by Владислав on 04.12.2017.
 */
public class ProductDAO extends AbstractDao<Product> {
    public ProductDAO(JDBCConnectionPool jdbcConnectionPool) {
        super(jdbcConnectionPool);
    }

    @Override
    public Product update() {
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
        return "delete from onlinestoreproject.products where id = ? ";
    }

    @Override
    public String getInsertQuery() {
        return "insert into onlinestoreproject.products(id,title,description,price,image_id) values(?,?,?,?,?)";
    }

    @Override
    protected List<Product> parseResultSet(ResultSet rs) {
        return null;
    }

    @Override
    protected void prepareStatemantForInsert(PreparedStatement statement, Product object) {
        try {
            statement.setInt(1, 0);
            statement.setString(2, object.getTitle());
            statement.setString(3,object.getDescription());
            statement.setInt(4, object.getPrice());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void prepareStatemantForDelete(PreparedStatement statement, Product object) {
        try {
            statement.setInt(1, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getByParameter(String parameter) {
        return null;
    }

    @Override
    public int[] insertImageAndProduct(Image image, Product product) {
        Connection connection = null;
        ResultSet resultSetImage = null;
        ResultSet resultSetProduct = null;
        PreparedStatement preparedStatementImageInsert = null;
        PreparedStatement preparedStatementProductInsert = null;
        String imageInser = "insert into onlinestoreproject.images( id, path, data) VALUES (?,?,?)";
        int rowAffected = 0;
        int imageId = 0;
        int productId = 0;
        try {
            connection = getConnectionFromPool();
            connection.setAutoCommit(false);
            preparedStatementImageInsert = connection.prepareStatement(imageInser, Statement.RETURN_GENERATED_KEYS);
            preparedStatementImageInsert.setInt(1, 0);
            preparedStatementImageInsert.setString(2, image.getPath());
            preparedStatementImageInsert.setString(3, image.getData());
            rowAffected = preparedStatementImageInsert.executeUpdate();
            resultSetImage = preparedStatementImageInsert.getGeneratedKeys();
            if (resultSetImage.next()) {
                imageId = resultSetImage.getInt(1);
            }
            if (rowAffected != 1) {
                connection.rollback();
            }
            preparedStatementProductInsert = connection.prepareStatement(getInsertQuery(), Statement.RETURN_GENERATED_KEYS);
            prepareStatemantForInsert(preparedStatementProductInsert,product);
            preparedStatementProductInsert.setInt(5, imageId);
            preparedStatementProductInsert.executeUpdate();
            resultSetProduct = preparedStatementProductInsert.getGeneratedKeys();
            if (resultSetProduct.next()) {
                productId = resultSetProduct.getInt(1);
            }

            connection.commit();

        } catch (SQLException e) {
            try {
                if (connection != null)
                    connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            closeResources(resultSetImage, preparedStatementImageInsert, connection);
            try {
                preparedStatementProductInsert.close();
                resultSetProduct.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new int[]{imageId, productId};
    }
}
