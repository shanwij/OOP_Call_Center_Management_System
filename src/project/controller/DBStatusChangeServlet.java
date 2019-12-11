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

import project.model.Complaint;
import project.model.Issue;
import project.service.ComplaintDAO;
import project.service.IssueDAO;
import project.util.ConnectionHandler;

@WebServlet("/DBStatusChangeServlet")
public class DBStatusChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DBStatusChangeServlet() {
        super();
    }
    
    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String databaseStatusChange = request.getParameter("dbstatuschange");
    	Connection connection = ConnectionHandler.connectDB();
    	if (databaseStatusChange != null) {
			String redirect = request.getParameter("redirect");
	    	switch (databaseStatusChange) {
	    		case "issuecomplaint":
	        		IssueDAO issueDao_ico = new IssueDAO(connection);
	        		ComplaintDAO issueComplaintDao_ico = new ComplaintDAO(connection);
	    			String issueCode_ico = request.getParameter("issuecode");
	    			String complaintCode_ico = request.getParameter("complaintcode");
	    			String complaintDesc_ico = request.getParameter("complaintdesc");
	    			Issue issue_ico = issueDao_ico.getData(issueCode_ico);
	    			issueComplaintDao_ico.addData(new Complaint(complaintCode_ico, issueCode_ico, complaintDesc_ico));
	    			issue_ico.setDate(new Date(System.currentTimeMillis()));
	    			issue_ico.setStatusCode("COMPLAINT");
	    			issueDao_ico.updateData(issue_ico);
	    			response.sendRedirect(redirect);
	    			break;
	    		case "issueclose":
	        		IssueDAO issueDao_icl = new IssueDAO(connection);
	    			String issueCode_icl = request.getParameter("issuecode");
	    			Issue issue_icl = issueDao_icl.getData(issueCode_icl);
	    			issue_icl.setDate(new Date(System.currentTimeMillis()));
	    			issue_icl.setStatusCode("CLOSED");
	    			issueDao_icl.updateData(issue_icl);
	    			response.sendRedirect(redirect);
	    			break;
	    		case "complaintclose":
	    			IssueDAO complaintIssueDao_ccl = new IssueDAO(connection);
	    			ComplaintDAO complaintDao_ccl = new ComplaintDAO(connection);
	    			String complaintCode_ccl = request.getParameter("complaintcode");
	    			Complaint complaint_ccl = complaintDao_ccl.getData(complaintCode_ccl);
	    			Issue complaintIssue_ccl = complaintIssueDao_ccl.getData(complaint_ccl.getIssueCode());
	    			complaintDao_ccl.removeData(complaintCode_ccl);
	    			complaintIssue_ccl.setStatusCode("CLOSED");
	    			complaintIssueDao_ccl.updateData(complaintIssue_ccl);
	    			response.sendRedirect(redirect);
	    			break;
	    	}
    	} else {
    		String databaseList = request.getParameter("dblist");
    		switch (databaseList) {
	    		case "issue":
	    			String issueCode = request.getParameter("issuecode");
	    			String issueStatus = request.getParameter("status");
	    			IssueDAO issueDao = new IssueDAO(connection);
	    			Issue issue = issueDao.getData(issueCode);
	    			request.setAttribute("oldissue", issue);
	    			request.setAttribute("status", issueStatus);
	    			request.setAttribute("databaselist", databaseList);
	    			break;
	    		case "complaint":
	    			String complaintCode = request.getParameter("complaintcode");
	    			request.setAttribute("oldcode", complaintCode);
	    			request.setAttribute("databaselist", databaseList);
	    			break;
	    	}
    		if (request.getAttribute("databaselist") != null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("DBStatusChange.jsp");
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
