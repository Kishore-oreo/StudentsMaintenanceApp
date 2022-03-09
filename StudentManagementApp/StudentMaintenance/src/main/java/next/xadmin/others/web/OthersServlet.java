package next.xadmin.others.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.xadmin.others.bean.OtherBean;
import next.xadmin.others.database.OtherDao;

/**
 * Servlet implementation class OthersServlet
 */
@WebServlet("/Others")
public class OthersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OthersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		OtherBean otherBean = new OtherBean();
		otherBean.setUsername(username);
		otherBean.setPassword(password);
		
		OtherDao otherDao = new OtherDao();
		
		if(otherDao.validate(otherBean))
		{
			response.sendRedirect("others");
		}
		else
		{
			response.sendRedirect("others.jsp");
		}
		
		
	}

}
