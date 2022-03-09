package com.xadmin.othermanagement.web;


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

import com.xadmin.othermanagement.bean.Otheruser;
import com.xadmin.othermanagement.dao.OtherUserDao;


/**
 * Servlet implementation class OtheruserServlet
 */
@WebServlet("/others")
public class OtheruserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private OtherUserDao otheruserdao;
  
	public void init() throws ServletException {
		 otheruserdao = new OtherUserDao();
	}
		
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		switch(action)
		{
		case "/onew":
			showNewForm(request, response);
			break;
		case "/oinsert":
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
		case "/odelete":
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
		case "/oedit":
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
		case "/oupdate":
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
					RequestDispatcher dispatcher = request.getRequestDispatcher("others-form.jsp");
					dispatcher.forward(request, response);
				}
				private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
				{
					String username = request.getParameter("username");
					String password = request.getParameter("password");
					Otheruser newUser = new Otheruser(username, password);
					
					otheruserdao.insertUser(newUser);
					response.sendRedirect("olist");
				}
				
				//delete user
				
				private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
				{
					int id = Integer.parseInt(request.getParameter("id"));
					try {
						otheruserdao.deleteUser(id);
					} catch (Exception e) {
						e.printStackTrace();
					}
					response.sendRedirect("olist");
				}
				//edit user
				private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
				{
					int id = Integer.parseInt(request.getParameter("id"));
					
					Otheruser existingUser;
					try {
						existingUser = otheruserdao.selectUser(id);
						RequestDispatcher dispatcher = request.getRequestDispatcher("others-form.jsp");
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
					String username = request.getParameter("username");
					String password = request.getParameter("password");
				
					Otheruser otheruser = new Otheruser(id, username, password);
					otheruserdao.updateUser(otheruser);
					response.sendRedirect("olist");
				}
				
				//default
				private void listUser(HttpServletRequest request, HttpServletResponse response) throws SQLException,ServletException ,IOException
				{
					try {
						List<Otheruser> listUser = otheruserdao.selectAllUsers();
						request.setAttribute("listUser", listUser);
						RequestDispatcher dispatcher = request.getRequestDispatcher("others-list.jsp");
						dispatcher.forward(request, response);
		 			} catch (Exception e) {
		 				e.printStackTrace();
		 			}
				}
	}
	

