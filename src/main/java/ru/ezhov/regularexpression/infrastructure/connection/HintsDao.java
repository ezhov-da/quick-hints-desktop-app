package ru.ezhov.regularexpression.infrastructure.connection;

import ru.ezhov.regularexpression.domain.Hint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HintsDao {
    private final Connection connection;

    public HintsDao(Connection connection) {
        this.connection = connection;
    }

    public List<Hint> select() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(Querys.SELECT)) {
                List<Hint> hints = new ArrayList<>();
                Hint hint;
                while (resultSet.next()) {
                    hint = new Hint();
                    hint.setId(resultSet.getInt("id"));
                    hint.setIdKey(resultSet.getString("idKey"));
                    hint.setText(resultSet.getString("text"));
                    hint.setDescription(resultSet.getString("description"));
                    hints.add(hint);
                }
                return hints;
            }
        }
    }

    public List<Hint> selectCondition(String condition) throws SQLException {
        if (condition != null) {
            try (PreparedStatement statement = connection.prepareStatement(Querys.SELECT_SEARCH)) {
                statement.setString(1, "%" + condition.toUpperCase() + "%");
                statement.setString(2, "%" + condition.toLowerCase() + "%");
                try (ResultSet resultSet = statement.executeQuery()) {
                    List<Hint> hints = new ArrayList<>();
                    Hint hint;
                    while (resultSet.next()) {
                        hint = new Hint();
                        hint.setId(resultSet.getInt("id"));
                        hint.setIdKey(resultSet.getString("idKey"));
                        hint.setText(resultSet.getString("text"));
                        hint.setDescription(resultSet.getString("description"));
                        hints.add(hint);
                    }
                    return hints;
                }
            }
        } else {
            throw new IllegalArgumentException("Condition can't be null");
        }
    }

    public String selectKey(int idKey) throws Exception {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Querys.SELECT_KEY)) {
            preparedStatement.setString(1, String.valueOf(idKey));
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                String returnString;
                if (resultSet.next()) {
                    returnString = resultSet.getString(1);
                } else {
                    returnString = "not found text for this key";
                }
                return returnString;
            }
        }
    }


    public void update(int id, String idKey, String text, String description) throws Exception {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Querys.UPDATE)) {
            preparedStatement.setString(1, idKey);
            preparedStatement.setString(2, text);
            preparedStatement.setString(3, description);
            preparedStatement.setInt(4, id);
            preparedStatement.execute();
        }
    }

    public void insert(String text, String description) throws Exception {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Querys.INSERT)) {
            preparedStatement.setString(1, text);
            preparedStatement.setString(2, description);
            preparedStatement.execute();
        }

    }

    public void delete(int id) throws Exception {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Querys.DELETE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }

    }
}