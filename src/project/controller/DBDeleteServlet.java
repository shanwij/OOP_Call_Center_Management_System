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

import project.model.Complaint;
import project.model.Issue;
import project.service.AgentDAO;
import project.service.ComplaintDAO;
import project.service.ContactDAO;
import project.service.IssueDAO;
import project.util.ConnectionHandler;

@WebServlet("/DBDeleteServlet")
public class DBDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DBDeleteServlet() {
        super();
    }
    
    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String databaseDelete = request.getParameter("dbdelete");
    	Connection connection = ConnectionHandler.connectDB();
    	if (databaseDelete != null) {
			String redirect = request.getParameter("redirect");
	    	switch (databaseDelete) {
	    		case "agent":
	        		AgentDAO agentDao = new AgentDAO(connection);
	    			String agentCode = request.getParameter("agentcode");
	    			agentDao.removeData(agentCode);
	    			response.sendRedirect(redirect);
		        	break;
	    		case "contact":
	        		ContactDAO contactDao = new ContactDAO(connection);
	    			String contactCode = request.getParameter("contactcode");
	    			contactDao.removeData(contactCode);
	    			response.sendRedirect(redirect);
		        	break;
	    		case "issue":
	    			ComplaintDAO issueComplaintDao = new ComplaintDAO(connection);
	    			List<Complaint> issueComplaints = issueComplaintDao.getDataList();
	    			IssueDAO issueDao = new IssueDAO(connection);
	    			String issueCode = request.getParameter("issuecode");
	    			for (Complaint complaint : issueComplaints)
	    				if (complaint.getIssueCode().equals(issueCode))
	    					issueComplaintDao.removeData(complaint.getCode());
	    			issueDao.removeData(issueCode);
	    			response.sendRedirect(redirect);
	    			break;
	    	}
    	} else {
    		String databaseList = request.getParameter("dblist");
    		switch (databaseList) {
	    		case "agent":
	    			String agentCode = request.getParameter("agentcode");
	    			request.setAttribute("oldcode", agentCode);
	    			request.setAttribute("databaselist", databaseList);
		        	break;
	    		case "contact":
	    			String contactCode = request.getParameter("contactcode");
	    			request.setAttribute("oldcode", contactCode);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("DBDelete.jsp");
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
