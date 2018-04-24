package ru.ezhov.regularexpression.connection;

import org.h2.jdbc.JdbcSQLException;
import ru.ezhov.regularexpression.ApplicationObject;

import java.sql.*;
import java.util.Vector;

/**
 * @author RRNDeonisiusEZH
 */
public class TreatmentsQuerys {
	private final Connection connection;

	public TreatmentsQuerys(Connection connection) {
		this.connection = connection;
	}


	public Vector<ApplicationObject> select(String query) throws SQLException {
		ResultSet resultSet = connection.createStatement().executeQuery(query);
		Vector<ApplicationObject> applicationObjects = new Vector<ApplicationObject>();


		ApplicationObject applicationObject;

		while (resultSet.next()) {
			applicationObject = new ApplicationObject();
			applicationObject.setId(resultSet.getInt("id"));
			applicationObject.setIdKey(resultSet.getString("idKey"));
			applicationObject.setText(resultSet.getString("text"));
			;
			applicationObject.setDescription(resultSet.getString("description"));

			applicationObjects.add(applicationObject);
		}


		resultSet.close();

		closeConnection();
		return applicationObjects;
	}


	public String selectKey(String query, int idKey) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, String.valueOf(idKey));
		ResultSet resultSet = preparedStatement.executeQuery();

		resultSet.next();

		String returnString;
		try {
			returnString = resultSet.getString(1);
		} catch (JdbcSQLException ex) {
			returnString = "not found text for this key";
		}

		preparedStatement.close();
		closeConnection();
		return returnString;
	}


	public void update(String query, int id, String idKey, String text, String description) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, idKey);
		preparedStatement.setString(2, text);
		preparedStatement.setString(3, description);
		preparedStatement.setInt(4, id);
		preparedStatement.execute();
		preparedStatement.close();

	}

	public void insert(String query, String text, String description) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, text);
		preparedStatement.setString(2, description);
		preparedStatement.execute();
		preparedStatement.close();

	}

	public void delete(String query, int id) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		preparedStatement.execute();
		preparedStatement.close();

	}

	private void closeConnection() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

}



