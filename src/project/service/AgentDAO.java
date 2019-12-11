package project.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.model.Agent;

public class AgentDAO implements DataAccessObject<Agent> {
	private static final String SQL_ADD_QUERY = "INSERT INTO agent(agentCode, agentName) VALUES(?, ?);";
	private static final String SQL_UPDATE_QUERY = "UPDATE agent SET agentName=? WHERE agentCode=?;";
	private static final String SQL_SELECT_QUERY = "SELECT agentCode, agentName FROM agent WHERE agentCode=?;";
	private static final String SQL_DELETE_QUERY = "DELETE FROM agent WHERE agentCode=?;";
	private static final String SQL_LIST_QUERY = "SELECT agentCode, agentName FROM agent;"; 
	private static final String SQL_LIST_COUNT_QUERY = "SELECT COUNT(agentCode) FROM agent;";
	
	Connection connection;
	
	public AgentDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void addData(Agent data) {
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_ADD_QUERY);
			statement.setString(1, data.getCode());
			statement.setString(2, data.getName());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateData(Agent data) {
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_QUERY);
			statement.setString(1, data.getName());
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
	public Agent getData(String code) {
		Agent agent = null;
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_SELECT_QUERY);
			statement.setString(1, code);
			ResultSet result = statement.executeQuery();
			if (result.next())
				agent = new Agent(result.getString(1), result.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return agent;
	}
	
	@Override
	public List<Agent> getDataList() {
		List<Agent> list = null;
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_LIST_QUERY);
			ResultSet result = statement.executeQuery();
			list = new ArrayList<>();
			while (result.next())
				list.add(new Agent(result.getString(1), result.getString(2)));
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
