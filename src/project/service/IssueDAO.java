package project.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.model.Issue;

public class IssueDAO implements DataAccessObject<Issue> {
	private static final String SQL_ADD_QUERY = "INSERT INTO issue(issueCode, customerCode, agentCode, issueDate, issueDescription, issueStatus) VALUES(?, ?, ?, ?, ?, ?);";
	private static final String SQL_UPDATE_QUERY = "UPDATE issue SET customerCode=?, agentCode=?, issueDate=?, issueDescription=?, issueStatus=? WHERE issueCode=?;";
	private static final String SQL_SELECT_QUERY = "SELECT issueCode, customerCode, agentCode, issueDate, issueDescription, issueStatus FROM issue WHERE issueCode=?;";
	private static final String SQL_DELETE_QUERY = "DELETE FROM issue WHERE issueCode=?;";
	private static final String SQL_LIST_QUERY = "SELECT issueCode, customerCode, agentCode, issueDate, issueDescription, issueStatus FROM issue;"; 
	private static final String SQL_LIST_COUNT_QUERY = "SELECT COUNT(issueCode) FROM issue;";

	Connection connection;
	
	public IssueDAO(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void addData(Issue data) {
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_ADD_QUERY);
			statement.setString(1, data.getCode());
			statement.setString(2, data.getCustomerCode());
			statement.setString(3, data.getAgentCode());
			statement.setDate(4, data.getDate());
			statement.setString(5, data.getDesc());
			statement.setString(6, data.getStatusCode());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateData(Issue data) {
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_QUERY);
			statement.setString(1, data.getCustomerCode());
			statement.setString(2, data.getAgentCode());
			statement.setDate(3, data.getDate());
			statement.setString(4, data.getDesc());
			statement.setString(5, data.getStatusCode());
			statement.setString(6, data.getCode());
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
	public Issue getData(String code) {
		Issue issue = null;
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_SELECT_QUERY);
			statement.setString(1, code);
			ResultSet result = statement.executeQuery();
			if (result.next())
				issue = new Issue(result.getString(1), result.getString(2), result.getString(3), result.getDate(4), result.getString(5), result.getString(6));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return issue;
	}

	@Override
	public List<Issue> getDataList() {
		List<Issue> list = null;
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_LIST_QUERY);
			ResultSet result = statement.executeQuery();
			list = new ArrayList<>();
			while (result.next())
				list.add(new Issue(result.getString(1), result.getString(2), result.getString(3), result.getDate(4), result.getString(5), result.getString(6)));
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
