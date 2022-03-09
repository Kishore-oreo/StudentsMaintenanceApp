package next.uploadusers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@MultipartConfig
@WebServlet("/uploadServlet")
public class uploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	/*
	 * public uploadServlet() { super(); // TODO Auto-generated constructor stub }
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String jdbcURL = "jdbc:mysql://localhost:3306/mydb";
		String jdbcUsername = "root";
		String jdbcPassword = "AdminMax18!";
		String jdbcDriver = "com.mysql.jdbc.Driver";

		//String csvFp = "D:\\csvdb.csv";
		// String csvFp = request.getParamseter(file);
		PrintWriter out = response.getWriter();

//		Part part = request.getPart("file");
//		String fileName = part.getSubmittedFileName();
//		System.out.println("FileName:" + fileName);
//		out.println(fileName);
		   
         String fileName = null ;

		try
		{
		//List<FileItem> multifiles = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(new ServletRequestContext(request));
			
			ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
			List<FileItem> multifiles = sf.parseRequest(request);
		
		for(FileItem item : multifiles)
		{
			File f = new File("/zoho/" + item.getName());
			fileName = item.getName();
			System.out.println(f.getAbsolutePath());
			item.write(f);
		    System.out.println("File uploaded"+fileName);
		
		}
	
		}
		catch(Exception e) 
		{
			System.out.println(e);
		    System.out.println("File not uploaded");
		}
	
		//ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());

		//	//	List<FileItem> multifiles = sf.parseRequest(request);
//		for(FileItem item: multifiles)
//		{
//			try {
//				item.write(new File("/Users/Lenovo/Documents"+item.getName()));
//				System.out.println("file created");
//			} catch (Exception e) {
//						e.printStackTrace();
//			}
//		}
		//String csvFp = getServletContext().getRealPath("D:/"+"zoho"+File.separator+fileName);
		String csvFp = "C:/"+"zoho"+File.separator+fileName;
		System.out.println("FileName:" + csvFp);
		// int batchsize=20;
		Connection connection = null;

		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			System.out.println("Connection Success");
			connection.setAutoCommit(false);

			final String SQL = "INSERT INTO others" + "(id,username, password) VALUES " + " ( ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(SQL);

			BufferedReader lineReader = new BufferedReader(new FileReader(csvFp));
//			 String lineText = null;
//			 int count = 0;
			// lineReader.readLine();
//			 while((lineText = lineReader.readLine()) != null)
//			 {
			// String[] data = lineText.split(",");

//				 System.out.println("data:"+data.toString());
			System.out.println("data printing");

//				 String usern = lineText.split(",")[0];
//				 
//				 String passw = lineText.split(",")[1];

			List<String> arraylines = new ArrayList<>();
			String line = null;

			while ((line = lineReader.readLine()) != null) {
				arraylines.add(line);
			}

			for (String ss : arraylines) {
				String id = ss.split(",")[0];
				String usern = ss.split(",")[1];

				String passw = ss.split(",")[2];
				System.out.println("splitted:" + usern);
				System.out.println("splitted:" + passw);

				// call insert query
				statement.setString(1, id);
				statement.setString(2, usern);
				statement.setString(3, passw);
				statement.executeUpdate();
			}

////				 
//				 System.out.println("splitted:"+usern);
//			 System.out.println("splitted:"+passw);
//				 //List<String> username = new ArrayList<String>();
//				 //List<String> password = new ArrayList<String>();
//				 //System.out.println(""+username);
//				 //System.out.println(""+password);	
//				 String username = data[0];
//				 String password = data[1];
//				 System.out.println(""+username);
//				 System.out.println(""+password);
//				 System.out.println("entry");
//				 

//				 statement.addBatch();
//				 if (count % batchsize == 0) {
//					 statement.executeBatch();
//				 }
			// }

			lineReader.close();

//			 statement.executeBatch();

			connection.commit();
			connection.close();

		} catch (IOException err1) {
			System.err.println(err1);
		} catch (SQLException err) {
			err.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("others");

	}

}
