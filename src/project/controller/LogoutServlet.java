package project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.model.Agent;
import project.util.QueueHandler;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogoutServlet() {
        super();
    }
    
    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String redirect = null;
    	String logoutFilter = request.getParameter("logout");
		HttpSession session = request.getSession();
    	if (logoutFilter != null) {
	    	switch (logoutFilter) {
	    		case "agent":
	    			redirect = "AgentLogin.jsp";
    				QueueHandler.dequeueAgent(((Agent) session.getAttribute("loginAgent")).getCode());
    				session.removeAttribute("loginAgent");
		        	break;
	    		case "admin":
	    			redirect = "AdminLogin.jsp";
    				session.removeAttribute("loginAdmin");
    				break;
	    	}
    	}
    	if (redirect != null) {
    		response.sendRedirect(redirect);
    	}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

}
