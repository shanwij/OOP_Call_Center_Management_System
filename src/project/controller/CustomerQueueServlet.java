package project.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.util.QueueHandler;

@WebServlet("/CustomerQueueServlet")
public class CustomerQueueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CustomerQueueServlet() {
        super();
    }
    
    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String customerCode = request.getParameter("customercode");
    	response.getWriter().append(Boolean.toString(QueueHandler.isInQueue(customerCode)));
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

}
