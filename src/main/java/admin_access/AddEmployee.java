package admin_access;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.Employee;

/**
 * Servlet implementation class AddEmployee
 */
@WebServlet("/admin/add_employee")
public class AddEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Logger logger=LoggerFactory.getLogger(AddEmployee.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String first_name=request.getParameter("first_name");
		String last_name=request.getParameter("last_name");
		String national_id=request.getParameter("national_id");
		int produce_id=Integer.parseInt(request.getParameter("produce_id"));
		
		Employee employee=Employee.makeEmployee(first_name, last_name,
				national_id, produce_id);
		
		if(employee.checkIfEmployeeExist(employee.getNational_id())==false) {
			employee.addEmployeeToDB();
		}else {
			response.getWriter().append("FUCKING EMPLOYEE EXISTS");
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
