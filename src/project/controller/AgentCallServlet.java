package project.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.model.Customer;
import project.model.Issue;
import project.service.CustomerDAO;
import project.service.DataAccessObject;
import project.service.IssueDAO;
import project.util.ConnectionHandler;
import project.util.QueueHandler;

@WebServlet("/AgentCallServlet")
public class AgentCallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AgentCallServlet() {
        super();
    }
    
    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String callAction = request.getParameter("call");
    	String agentCode = request.getParameter("agentcode");
    	String customerCode = request.getParameter("customercode");
    	switch (callAction) {
    		case "start":
		    	Connection connection = ConnectionHandler.connectDB();
		    	DataAccessObject<Issue> issueDao = new IssueDAO(connection);
		    	DataAccessObject<Customer> customerDao = new CustomerDAO(connection);
		    	List<Issue> list = issueDao.getDataList();
		    	List<Issue> data = new ArrayList<>();
		    	for (Issue issue : list)
			    	if (issue.getAgentCode().equals(agentCode) && issue.getCustomerCode().equals(customerCode))
			    		data.add(issue);
		    	Customer customer = customerDao.getData(customerCode);
		    	request.setAttribute("agentcode", agentCode);
		    	request.setAttribute("customercode", customerCode);
		    	request.setAttribute("customerstatus", customer.getStatusCode());
		    	request.setAttribute("data", data);
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("AgentCall.jsp");
		    	dispatcher.forward(request, response);
		    	break;
    		case "end":
    			QueueHandler.dequeueCustomer(customerCode);
    			response.sendRedirect("AgentIdle.jsp");
    	}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

}
