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
		
		Customer.Builder customerBuilder=new Builder();
		customerBuilder.setAll(first_name, last_name,
				user_name, phone_no, password);
		
		Customer customer=customerBuilder.build();
		customer.addToDB();
		
		response.getWriter().append("Served at: ")
			.append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
