package admin_access;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/admin/admin_login")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user_name=request.getParameter("user_name").trim().toLowerCase();
		String password=request.getParameter("password").trim().toLowerCase();
		
		if(user_name==null || password==null) {
			String admin_error="Please fill in proper information";
			request.getSession().setAttribute("admin_error", admin_error);
			response.sendRedirect("index.jsp");
			return;
		}
		if(user_name.equals(password) && user_name.equals("admin")) {
			response.sendRedirect("page.jsp");
		}else {
			String admin_error="Please fill in proper information";
			request.getSession().setAttribute("admin_error", admin_error);
			response.sendRedirect("index.jsp");
			return;
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
