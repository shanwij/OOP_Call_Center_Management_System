package project.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.model.Complaint;

public class ComplaintDAO implements DataAccessObject<Complaint> {
	private static final String SQL_ADD_QUERY = "INSERT INTO complaint(complaintCode, issueCode, complaintDescription) VALUES(?, ?, ?);";
	private static final String SQL_UPDATE_QUERY = "UPDATE complaint SET issueCode=?, complaintDescription=? WHERE complaintCode=?;";
	private static final String SQL_SELECT_QUERY = "SELECT complaintCode, issueCode, complaintDescription FROM complaint WHERE complaintCode=?;";
	private static final String SQL_DELETE_QUERY = "DELETE FROM complaint WHERE complaintCode=?;";
	private static final String SQL_LIST_QUERY = "SELECT complaintCode, issueCode, complaintDescription FROM complaint"; 
	private static final String SQL_LIST_COUNT_QUERY = "SELECT COUNT(complaintCode) FROM complaint;";
	
	Connection connection;
	
	public ComplaintDAO(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void addData(Complaint data) {
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_ADD_QUERY);
			statement.setString(1, data.getCode());
			statement.setString(2, data.getIssueCode());
			statement.setString(3, data.getDesc());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateData(Complaint data) {
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_QUERY);
			statement.setString(1, data.getIssueCode());
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
	public Complaint getData(String code) {
		Complaint complaint = null;
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_SELECT_QUERY);
			statement.setString(1, code);
			ResultSet result = statement.executeQuery();
			if (result.next())
				complaint = new Complaint(result.getString(1), result.getString(2), result.getString(3));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return complaint;
	}

	@Override
	public List<Complaint> getDataList() {
		List<Complaint> list = null;
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_LIST_QUERY);
			ResultSet result = statement.executeQuery();
			list = new ArrayList<>();
			while (result.next())
				list.add(new Complaint(result.getString(1), result.getString(2), result.getString(3)));
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
