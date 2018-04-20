package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateStatementStrategy implements StatementStrategy {
    private final Product product;

    public UpdateStatementStrategy(Product product) {
        this.product = product;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE product SET title = ? , price = ? WHERE id = ?");
        preparedStatement.setString(1, product.getTitle());
        preparedStatement.setLong(2, product.getPrice());
        preparedStatement.setLong(3, product.getId());

        return preparedStatement;
    }
}
