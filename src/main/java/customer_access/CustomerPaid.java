package customer_access;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.Customer;
import models.Produce;

/**
 * Servlet implementation class CustomerPaid
 */
@WebServlet("/guest/customer_paid")
public class CustomerPaid extends HttpServlet {
	//manage deducting from store amount used
	private static final long serialVersionUID = 1L;
	
	private Logger logger=LoggerFactory.getLogger(CustomerPaid.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerPaid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HashMap<String, String> values=
				(HashMap<String, String>) 
				request.getSession().getAttribute("values");
		
		//get user credentials
		HashMap<String, String> user=
				(HashMap<String, String>)
				request.getSession().getAttribute("user");
		//get current logged in user
		Customer customer=Customer.
				getFromDB(user.get("user_name"), user.get("password"));
		
		String mpesa_code=request.getParameter("mpesa_code");
		
		//get all products
		Set<String> keys=values.keySet();
		for(String key:keys) {
			Produce produce=Produce.getProduceWithName(key);
			int quantity=Integer.parseInt(values.get(key));
			customer.payGoodFor(produce, quantity, mpesa_code);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
