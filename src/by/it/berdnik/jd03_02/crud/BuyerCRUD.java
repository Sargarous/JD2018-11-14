package by.it.berdnik.jd03_02.crud;

import by.it.berdnik.jd03_02.beans.Buyer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BuyerCRUD {

    boolean create(Buyer buyer) throws SQLException {
        String sql = String.format(
                "INSERT INTO `buyers` (`item`, `specif`, `price`,`address`, `users_id`) " +
                        "VALUES ('%s', '%s', '%d', '%s', '%d')",
                buyer.getItem(), buyer.getSpecif(), buyer.getPrice(), buyer.getAddress(), buyer.getUsers_Id());
        try (Connection connection = Connect.getConnection();
             Statement statement = connection.createStatement()) {
            if (1 == statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS)) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    buyer.setId(generatedKeys.getLong(1));
                }
                return true;
            }
        }
        return false;
    }

    Buyer read(long id) throws SQLException {
        String sql = String.format("SELECT `item`, `specif`, `price`,`address`, `users_id` " +
                "FROM `buyers` WHERE id=%d", id);
        try (Connection connection = Connect.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                String item = resultSet.getString("item");
                String specif = resultSet.getString("specif");
                double price = resultSet.getLong("price");
                String address = resultSet.getString("address");
                long users_id = resultSet.getLong("users_id");
                return new Buyer(id, item, specif, price, address, users_id);
            } else
                return null;
        }
    }

    boolean update(Buyer buyer) throws SQLException {
        String sql = String.format(
                "UPDATE `buyers` SET " +
                        "`item` = '%s', `specif` = '%s', " +
                        "`price` = '%d', `address` = '%s', `users_id` = '%d' " +
                        "WHERE `buyers`.`id` = %d",
                buyer.getItem(), buyer.getSpecif(), buyer.getPrice(),
                buyer.getAddress(), buyer.getId());
        try (Connection connection = Connect.getConnection();
             Statement statement = connection.createStatement()) {
            return (1 == statement.executeUpdate(sql));
        }
    }

    boolean delete(Buyer buyer) throws SQLException {
        String sql = String.format(
                "DELETE FROM `buyers` WHERE `users`.`id` = %d",
                buyer.getId());
        try (Connection connection = Connect.getConnection();
             Statement statement = connection.createStatement()) {
            return (1 == statement.executeUpdate(sql));
        }
    }
}