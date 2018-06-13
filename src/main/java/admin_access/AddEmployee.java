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
		int produce_id=-1;
		String first_name=request.getParameter("first_name");
		String last_name=request.getParameter("last_name");
		String national_id=request.getParameter("national_id");
		produce_id=Integer.parseInt(request.getParameter("produce_id"));
		
		if(first_name== null || last_name==null || national_id==null || produce_id==-1) {
			String admin_error="Please retry and fill in all fields this time";
			request.getSession().setAttribute("admin_error", admin_error);
			request.getRequestDispatcher("page.jsp").forward(request, response);
		}
		
		if(first_name.length()<=0 || last_name.length()<=0 || national_id.length()<=7) {
			String admin_error="Make sure you have given first and last name, id should be valid";
			request.getSession().setAttribute("admin_error", admin_error);
			request.getRequestDispatcher("page.jsp").forward(request, response);
		}
		
		Employee employee=Employee.makeEmployee(first_name, last_name,
				national_id, produce_id);
		
		if(employee.checkIfEmployeeExist(employee.getNational_id())==false) {
			employee.addEmployeeToDB();
			response.sendRedirect("page.jsp");
		}else {
			String admin_error="Sorry Employee already exists";
			request.getSession().setAttribute("admin_error", admin_error);
			response.sendRedirect("page.jsp");
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
