package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class ProductDao {
    private final DataSource dataSource;

    public ProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Product get(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id = ?");
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setTitle(resultSet.getString("title"));
        product.setPrice(resultSet.getInt("price"));

        //자원을 해지한다.
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return product;
    }

    public Long insert(Product product) throws SQLException {
        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement =
                connection.prepareStatement("INSERT INTO product(title,price) VALUES (?,?)");
        preparedStatement.setString(1, product.getTitle());
        preparedStatement.setLong(2, product.getPrice());

        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement("SELECT last_insert_id()");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        Long id = resultSet.getLong(1);

        //자원을 해지한다.
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return id;
    }
}
