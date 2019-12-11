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

import project.model.Contact;
import project.model.Customer;
import project.service.ContactDAO;
import project.service.CustomerDAO;
import project.util.ConnectionHandler;
import project.util.QueueHandler;

@WebServlet("/CustomerCallServlet")
public class CustomerCallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CustomerCallServlet() {
        super();
    }

    
    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String callAction = request.getParameter("call");
    	HttpSession session = request.getSession();
    	switch (callAction) {
    		case "start":
    			String forward = "CustomerIdle.jsp";
		    	Connection connection = ConnectionHandler.connectDB();
		    	String contactCode = request.getParameter("contactcode");
    			if (!contactCode.equals("") && contactCode.matches("^[0-9*#]+$")) {
			    	ContactDAO contactDao = new ContactDAO(connection);
			    	Contact contact = contactDao.getData(contactCode);
			    	String code = "NULL";
			    	if (contact != null) {
			    		code = contact.getType();
			    	}
			    	switch (code) {
			    		case "CALL":
			    			String customerCode = request.getParameter("customercode");
			    			if (!customerCode.equals("") && customerCode.matches("^[0-9]{10}$")) {
				    			CustomerDAO customerDao = new CustomerDAO(connection);
				    			Customer customer = customerDao.getData(customerCode);
				    			if (customer == null) {
				    				customer = new Customer(customerCode, "UNREGISTERED");
				    				customerDao.addData(customer);
				    			}
				    			if (QueueHandler.enqueueCustomer(customerCode)) {
					    			session.setAttribute("loginCustomer", customer);
					    			forward = "CustomerCall.jsp";
				    			}
			    			}
			    			break;
			    	}
    			}
		    	RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		    	dispatcher.forward(request, response);
		    	break;
    		case "end":
    			Customer customer = (Customer) session.getAttribute("loginCustomer");
    			if (customer != null) {
    				QueueHandler.dequeueCustomer(customer.getCode());
    				session.removeAttribute("loginCustomer");
    			}
    			response.sendRedirect("CustomerIdle.jsp");
    			break;
    	}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

}
