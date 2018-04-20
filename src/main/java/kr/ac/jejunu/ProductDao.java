package kr.ac.jejunu;

import java.sql.*;

public class ProductDao {
    private final JdbcContext jdbcContext;

    public ProductDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public Product get(Long id) throws SQLException {
        StatementStrategy statementStrategy = connection ->  {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id = ?");
            preparedStatement.setLong(1, id);

            return preparedStatement;

        };
        return jdbcContext.jdbcContextForGet(statementStrategy);
    }

    public Long insert(Product product) throws SQLException {
        StatementStrategy statementStrategy = connection ->  {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product(title,price) VALUES (?,?)");
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setLong(2, product.getPrice());

            return preparedStatement;

        };
        return jdbcContext.jdbcContextForInsert(statementStrategy);
    }

    public void update(Product product) throws SQLException {
        StatementStrategy statementStrategy = connection ->  {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE product SET title = ? , price = ? WHERE id = ?");
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setLong(2, product.getPrice());
            preparedStatement.setLong(3, product.getId());

            return preparedStatement;

        };
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    public void delete(Long id) throws SQLException {
        StatementStrategy statementStrategy =connection ->  {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM product WHERE id = ?");
            preparedStatement.setLong(1, id);

            return preparedStatement;

        };
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }
}
