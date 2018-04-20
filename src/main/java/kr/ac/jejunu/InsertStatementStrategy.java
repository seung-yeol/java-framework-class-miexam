package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertStatementStrategy implements StatementStrategy {
    private final Product product;

    public InsertStatementStrategy(Product product) {
        this.product = product;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product(title,price) VALUES (?,?)");
        preparedStatement.setString(1, product.getTitle());
        preparedStatement.setLong(2, product.getPrice());

        return preparedStatement;
    }
}
