package project.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.model.Agent;
import project.service.AgentDAO;
import project.util.ConnectionHandler;
import project.util.QueueHandler;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }
    
    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String forward = null;
    	String loginFilter = request.getParameter("login");
		HttpSession session = request.getSession();
    	if (loginFilter != null) {
	    	switch (loginFilter) {
	    		case "agent":
	    			forward = "AgentLogin.jsp";
	    	    	Connection connection = ConnectionHandler.connectDB();
	        		AgentDAO agentDao = new AgentDAO(connection);
	    			String agentCode = request.getParameter("agentcode");
	    			Agent loginAgent = agentDao.getData(agentCode);
	    			if (loginAgent != null) {
	    				forward = "AgentIdle.jsp";
	    				QueueHandler.enqueueAgent(agentCode);
	    				session.setAttribute("loginAgent", loginAgent);
	    			}
		        	break;
	    		case "admin":
	    			forward = "AdminLogin.jsp";
	    			String username = request.getParameter("username");
	    			String password = request.getParameter("password");
	    			if (ConnectionHandler.authenticate(username, password)) {
	    				forward = "AdminDB.jsp";
	    				session.setAttribute("loginAdmin", session.getId());
	    			}
		        	break;
	    	}
    	}
    	if (forward != null) {
    		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
    		dispatcher.forward(request, response);
    	}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

}
