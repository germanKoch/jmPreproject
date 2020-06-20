package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection;


    public UserDaoJDBCImpl() {

    }

    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createUsersTable() {
        try (Statement stmt = connection.createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS Users (" +
                    " id BIGINT(20) NOT NULL AUTO_INCREMENT," +
                    " name VARCHAR(45) NOT NULL," +
                    " last_name VARCHAR(45) NULL," +
                    " age TINYINT(3) NULL," +
                    " PRIMARY KEY (id)," +
                    " INDEX `index2` (name ASC, last_name ASC) VISIBLE)";
            stmt.execute(query);
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS Users");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO " +
                "Users(name, last_name, age) VALUES (?,?,?)")) {
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setByte(3, age);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM Users " +
                "WHERE id = ?")) {
            stmt.setLong(1, id);
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute("SELECT * FROM Users");
            ResultSet resultSet = stmt.getResultSet();

            while (resultSet.next()) {
                result.add(new User(resultSet.getLong("id"), resultSet.getString("name"),
                        resultSet.getString("last_name"), resultSet.getByte("age")));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return result;
    }

    @Override
    public void cleanUsersTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM Users");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
