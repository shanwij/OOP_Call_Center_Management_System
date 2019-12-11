package project.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.service.AgentDAO;
import project.service.ComplaintDAO;
import project.service.ContactDAO;
import project.service.CustomerDAO;
import project.service.DataAccessObject;
import project.service.IssueDAO;
import project.util.ConnectionHandler;

@WebServlet("/DBListServlet")
public class DBListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DBListServlet() {
		super();
	}
    
    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Connection connection = ConnectionHandler.connectDB();
    	String databaseList = request.getParameter("dblist");
    	DataAccessObject<?> dao;
    	switch (databaseList) {
	    	case "agent":
				dao = new AgentDAO(connection);
				break;
	    	case "contact":
				dao = new ContactDAO(connection);
				break;
			case "customer":
				dao = new CustomerDAO(connection);
				break;
			case "issue":
				dao = new IssueDAO(connection);
				break;
			case "complaint":
				dao = new ComplaintDAO(connection);
				break;
			default:
				dao = null;
    	}
    	List<?> data;
    	if (dao != null)
    		data = dao.getDataList();
    	else
    		data = null;
    	request.setAttribute("data", data);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("DBList.jsp");
    	dispatcher.forward(request, response);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

}
