package com.xadmin.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xadmin.usermanagement.bean.User;
import com.xadmin.usermanagement.dao.UserDao;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
       private UserDao userDao;
       

    
	public void init() throws ServletException {
				userDao = new UserDao();
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//	          boolean flag = false;
//	    	   HttpSession ss = request.getSession();
//	    	   if(ss!=null) {
//	    		   doGet(request, response);
//	}
//	    	   else {
//	    		   System.out.println("Please Login !");
//	    		  // response.getWriter("PLS LOGIN ");
//	    		   request.getRequestDispatcher("/index.jsp").forward(request, response);
//	    	   }
		
		
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub 
		String action = request.getServletPath();
	
		switch(action)
		{
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
			 try {
				insertUser(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/delete":
			try {
				deleteUser(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/edit":
			try {
				showEditForm(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/update":
			try {
				updateUser(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			break;
			default:
			try {
				listUser(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
		}
	}
	
	//new user
		private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
			dispatcher.forward(request, response);
		}
		private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
		{
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String department = request.getParameter("department");
			User newUser = new User(name, email, department);
			
			userDao.insertUser(newUser);
			response.sendRedirect("list");
		}
		
		//delete user
		
		private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
		{
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				userDao.deleteUser(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect("list");
		}
		//edit user
		private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
		{
			int id = Integer.parseInt(request.getParameter("id"));
			
			User existingUser;
			try {
				existingUser = userDao.selectUser(id);
				RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
				request.setAttribute("user", existingUser);
				dispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
					
		}
		
		//update 
		private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
		{
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String department = request.getParameter("department");
			
			User user = new User(id, name, email, department);
			userDao.updateUser(user);
			response.sendRedirect("list");
		}
		
		//default
		private void listUser(HttpServletRequest request, HttpServletResponse response) throws SQLException,ServletException ,IOException
		{
			try {
				List<User> listUser = userDao.selectAllUsers();
				request.setAttribute("listUser", listUser);
				RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
				dispatcher.forward(request, response);
 			} catch (Exception e) {
 				e.printStackTrace();
 			}
		}
		
	}

	
	


