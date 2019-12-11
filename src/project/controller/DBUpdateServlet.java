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

@WebServlet("/DBUpdateServlet")
public class DBUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DBUpdateServlet() {
        super();
    }
    
    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String databaseUpdate = request.getParameter("dbupdate");
    	Connection connection = ConnectionHandler.connectDB();
    	if (databaseUpdate != null) {
			String redirect = request.getParameter("redirect");
	    	switch (databaseUpdate) {
	    		case "agent":
	        		AgentDAO agentDao = new AgentDAO(connection);
	    			String agentCode = request.getParameter("agentcode");
	    			String agentName = request.getParameter("agentname");
	    			Agent newAgent = new Agent(agentCode, agentName);
	    			agentDao.updateData(newAgent);
	    			response.sendRedirect(redirect);
		        	break;
	    		case "contact":
	        		ContactDAO contactDao = new ContactDAO(connection);
	    			String contactCode = request.getParameter("contactcode");
	    			String contactType = request.getParameter("contacttype");
	    			String contactDesc = request.getParameter("contactdesc");
	    			Contact newContact = new Contact(contactCode, contactType, contactDesc);
	    			contactDao.updateData(newContact);
	    			response.sendRedirect(redirect);
	    			break;
	    		case "issue":
	        		IssueDAO issueDao = new IssueDAO(connection);
	    			String issueCode = request.getParameter("issuecode");
	    			String issueCustomerCode = request.getParameter("customercode");
	    			String issueAgentCode = request.getParameter("agentcode");
	    			String issueDesc = request.getParameter("issuedesc");
	    			Issue newIssue = new Issue(issueCode, issueCustomerCode, issueAgentCode, new Date(System.currentTimeMillis()), issueDesc, "ACTIVE");
	    			issueDao.updateData(newIssue);
	    			response.sendRedirect(redirect);
	    			break;
	    	}
    	} else {
    		String databaseList = request.getParameter("dblist");
    		switch (databaseList) {
	    		case "agent":
	    			String agentCode = request.getParameter("agentcode");
	    			AgentDAO agentDao = new AgentDAO(connection);
	    			Agent agent = agentDao.getData(agentCode);
	    			request.setAttribute("oldagent", agent);
	    			request.setAttribute("databaselist", databaseList);
		        	break;
	    		case "contact":
	    			String contactCode = request.getParameter("contactcode");
	    			ContactDAO contactDao = new ContactDAO(connection);
	    			Contact contact = contactDao.getData(contactCode);
	    			request.setAttribute("oldcontact", contact);
	    			request.setAttribute("databaselist", databaseList);
	    			break;
	    		case "issue":
	    			String issueCode = request.getParameter("issuecode");
	    			IssueDAO issueDao = new IssueDAO(connection);
	    			Issue issue = issueDao.getData(issueCode);
	    			request.setAttribute("oldissue", issue);
	    			request.setAttribute("databaselist", databaseList);
	    			break;
	    	}
    		if (request.getAttribute("databaselist") != null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("DBUpdate.jsp");
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
