package project.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.model.Agent;
import project.model.Contact;
import project.model.Issue;
import project.service.AgentDAO;
import project.service.ContactDAO;
import project.service.IssueDAO;
import project.util.ConnectionHandler;

@WebServlet("/DBAddServlet")
public class DBAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DBAddServlet() {
		super();
	}
    
    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String databaseAdd = request.getParameter("dbadd");
    	Connection connection = ConnectionHandler.connectDB();
    	if (databaseAdd != null) {
    		String redirect = request.getParameter("redirect");
	    	switch (databaseAdd) {
	    		case "agent":
	    			String agentCode = request.getParameter("agentcode");
	    			String agentName = request.getParameter("agentname");
	    			if (agentCode != null && agentCode.matches("^AGT_[0-9]+$")) {
		        		AgentDAO agentDao = new AgentDAO(connection);
		    			Agent newAgent = new Agent(agentCode, agentName);
		    			agentDao.addData(newAgent);
	    			}
	    			response.sendRedirect(redirect);
		        	break;
	    		case "contact":
	    			String contactCode = request.getParameter("contactcode");
	    			String contactType = request.getParameter("contacttype");
	    			String contactDesc = request.getParameter("contactdesc");
	        		ContactDAO contactDao = new ContactDAO(connection);
	        		if (contactCode != null && contactCode.matches("^[0-9*#]+$")) {
		    			Contact newContact = new Contact(contactCode, contactType, contactDesc);
		    			contactDao.addData(newContact);
	        		}
	    			response.sendRedirect(redirect);
		        	break;
	    		case "issue":
	    			String issueCode = request.getParameter("issuecode");
	    			String issueCustomerCode = request.getParameter("customercode");
	    			String issueAgentCode = request.getParameter("agentcode");
	    			String issueDesc = request.getParameter("issuedesc");
	    			if (issueCode != null && issueCode.matches("^ISS_[0-9]+$")) {
		    			IssueDAO issueDao = new IssueDAO(connection);
		    			Issue newIssue = new Issue(issueCode, issueCustomerCode, issueAgentCode, new Date(System.currentTimeMillis()), issueDesc, "ACTIVE");
		    			issueDao.addData(newIssue);
	    			}
	    			response.sendRedirect(redirect);
		        	break;
	    	}
    	} else {
    		String databaseList = request.getParameter("dblist");
    		switch (databaseList) {
	    		case "agent":
	    			request.setAttribute("databaselist", databaseList);
		        	break;
	    		case "contact":
	    			request.setAttribute("databaselist", databaseList);
		        	break;
	    		case "issue":
	    			String customerCode = request.getParameter("customercode");
	    			String agentCode = request.getParameter("agentcode");
	    			request.setAttribute("customerref", customerCode);
	    			request.setAttribute("agentref", agentCode);
	    			request.setAttribute("databaselist", databaseList);
		        	break;
	    	}
    		if (request.getAttribute("databaselist") != null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("DBAdd.jsp");
				dispatcher.forward(request, response);
    		}
    	}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

}
