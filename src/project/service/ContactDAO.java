package project.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.model.Contact;

public class ContactDAO implements DataAccessObject<Contact> {
	private static final String SQL_ADD_QUERY = "INSERT INTO contact(contactCode, contactType, contactDescription) VALUES(?, ?, ?);";
	private static final String SQL_UPDATE_QUERY = "UPDATE contact SET contactType=?, contactDescription=? WHERE contactCode=?;";
	private static final String SQL_SELECT_QUERY = "SELECT contactCode, contactType, contactDescription FROM contact WHERE contactCode=?;";
	private static final String SQL_DELETE_QUERY = "DELETE FROM contact WHERE contactCode=?;";
	private static final String SQL_LIST_QUERY = "SELECT contactCode, contactType, contactDescription FROM contact;"; 
	private static final String SQL_LIST_COUNT_QUERY = "SELECT COUNT(contactCode) FROM contact;";
	
	Connection connection;
	
	public ContactDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void addData(Contact data) {
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_ADD_QUERY);
			statement.setString(1, data.getCode());
			statement.setString(2, data.getType());
			statement.setString(3, data.getDesc());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateData(Contact data) {
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_QUERY);
			statement.setString(1, data.getType());
			statement.setString(2, data.getDesc());
			statement.setString(3, data.getCode());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeData(String code) {
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_DELETE_QUERY);
			statement.setString(1, code);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Contact getData(String code) {
		Contact contact = null;
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_SELECT_QUERY);
			statement.setString(1, code);
			ResultSet result = statement.executeQuery();
			if (result.next())
				contact = new Contact(result.getString(1), result.getString(2), result.getString(3));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contact;
	}

	@Override
	public List<Contact> getDataList() {
		List<Contact> list = null;
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_LIST_QUERY);
			ResultSet result = statement.executeQuery();
			list = new ArrayList<>();
			while (result.next())
				list.add(new Contact(result.getString(1), result.getString(2), result.getString(3)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getDataListSize() {
		int count = -1;
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_LIST_COUNT_QUERY);
			ResultSet result = statement.executeQuery();
			if (result.next())
				count = result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

}
