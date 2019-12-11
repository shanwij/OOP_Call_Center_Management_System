package project.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.model.Customer;

public class CustomerDAO implements DataAccessObject<Customer> {
	private static final String SQL_ADD_QUERY = "INSERT INTO customer(customerCode, customerStatus) VALUES(?, ?);";
	private static final String SQL_UPDATE_QUERY = "UPDATE customer SET customerStatus=? WHERE customerCode=?;";
	private static final String SQL_SELECT_QUERY = "SELECT customerCode, customerStatus FROM customer WHERE customerCode=?;";
	private static final String SQL_DELETE_QUERY = "DELETE FROM customer WHERE customerCode=?;";
	private static final String SQL_LIST_QUERY = "SELECT customerCode, customerStatus FROM customer;"; 
	private static final String SQL_LIST_COUNT_QUERY = "SELECT COUNT(customerCode) FROM customer;";

	Connection connection;
	
	public CustomerDAO(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void addData(Customer data) {
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_ADD_QUERY);
			statement.setString(1, data.getCode());
			statement.setString(2, data.getStatusCode());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateData(Customer data) {
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_QUERY);
			statement.setString(1, data.getStatusCode());
			statement.setString(2, data.getCode());
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
	public Customer getData(String code) {
		Customer customer = null;
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_SELECT_QUERY);
			statement.setString(1, code);
			ResultSet result = statement.executeQuery();
			if (result.next())
				customer = new Customer(result.getString(1), result.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public List<Customer> getDataList() {
		List<Customer> list = null;
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_LIST_QUERY);
			ResultSet result = statement.executeQuery();
			list = new ArrayList<>();
			while (result.next())
				list.add(new Customer(result.getString(1), result.getString(2)));
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
