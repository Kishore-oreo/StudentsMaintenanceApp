package next.Filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class MyFilter
 */
@WebFilter("/login")
public class MyFilter extends HttpFilter implements Filter {
       
    
	public void init(FilterConfig fConfig) throws ServletException {
		
	}
    public MyFilter() {
        
    }

	
	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("Filter is called");
		
		
	    PrintWriter out=response.getWriter();  
        
	    String password=request.getParameter("password");
//	    HttpServletRequest ssss = (HttpServletRequest) req;
	    String username = (String) ((HttpServletRequest) request).getSession().getAttribute("username");
 //   HttpSession ss = ssss.getSession();
	    if(password.equals("admin")){  
	    chain.doFilter(request, response);//sends request to next resource  
	    RequestDispatcher rd =request.getRequestDispatcher("list");
	    System.out.println("Filter: password correct");
	    }  
	    else{  
//	    out.print("username or password error!");  
	    RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
	    rd.include(request, response);  
	    System.out.println("Filter: password wrong");
	    }  
		
		/**chain.doFilter(request, response); */
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */

}
