package customer_access;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.Customer;
import models.Customer.Builder;

/**
 * Servlet implementation class CustomerRegistration
 */
@WebServlet("/guest/customer_register")
public class CustomerRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Logger logger=LoggerFactory.getLogger(CustomerRegistration.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Parameters from server
		String first_name=request.getParameter("first_name");
		String last_name=request.getParameter("last_name");
		String user_name=request.getParameter("user_name");
		String phone_no=request.getParameter("phone_no");
		String password=request.getParameter("password");
		
		if(first_name==null || last_name==null || user_name==null
				|| phone_no==null || password==null) {
			String user_error="Please fill in proper details before registration";
			request.getSession().setAttribute("user_error", user_error);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		if(first_name.length()==0 || last_name.length()==0 || user_name.length()==0
				|| phone_no.length()<10 || password.length()==0) {
			String user_error="Please fill in proper details before registration";
			request.getSession().setAttribute("user_error", user_error);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		Customer.Builder customerBuilder=new Builder();
		customerBuilder.setAll(first_name, last_name,
				user_name, phone_no, password);
		
		Customer customer=customerBuilder.build();
		try{
			customer.addToDB();
		}catch(Exception ex) {
			request.getSession().setAttribute("user_error", "Sorry, User Name already in use");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		request.getSession().setAttribute("user_error", "Okay, now login with created account");
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
