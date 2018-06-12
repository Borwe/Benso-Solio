package admin_access;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.Produce;

/**
 * Servlet implementation class AddProduce
 */
@WebServlet("/admin/add_produce")
public class AddProduce extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Logger logger=LoggerFactory.getLogger(AddProduce.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProduce() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Calendar today=Calendar.getInstance();
		String date=today.get(Calendar.DAY_OF_MONTH)+"/"
				+(today.get(Calendar.MONTH)+1)+"/"+
				today.get(Calendar.YEAR);
		
		String product_name=request.getParameter("produce_name");
		int category_id=Integer.parseInt(request.getParameter("category_id"));
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		double price=Double.parseDouble(request.getParameter("price"));
		
		Produce produce=Produce.createProduce(
				product_name,category_id,quantity,price, date);
		
		produce.addToDB();
		
		response.sendRedirect("page.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
