package customer_access;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.Produce;

/**
 * Servlet implementation class ConfirmList
 */
@WebServlet("/guest/confirm_list")
public class ConfirmList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Logger logger=LoggerFactory.getLogger(ConfirmList.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<Produce> produceList=Produce.getProduceList();
		Enumeration<String> params=request.getParameterNames();
		
		HashMap<String,String> values=new HashMap<>();
		
		while(params.hasMoreElements()){
			String element=params.nextElement();
			//skip if quantity is == 0
			if(request.getParameter(element).equals("0")){
				continue;
			}
			values.put(element, request.getParameter(element));
		}
		
		request.getSession().putValue("values", values);
		
		response.sendRedirect("prompt_buy.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
