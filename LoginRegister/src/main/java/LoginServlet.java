import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate username and password against the database
        try {
        	 HttpSession session = request.getSession();
			if (isValidUser(username, password)) {
				session.setAttribute("message", "Login successful");
			    session.setAttribute("username", username);
			    System.out.println("Logged in");
			    response.sendRedirect("jsp/welcome.jsp");
			} else {
				session.setAttribute("message", "Login unsuccessful");
			    response.sendRedirect("jsp/login.jsp");
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			 HttpSession session = request.getSession();
			session.setAttribute("message", "Exception occurred");
			response.sendRedirect("jsp/login.jsp");
			e.printStackTrace();
		}
    }

    private boolean isValidUser(String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/auth","shrikant","Shrikant@123");
        PreparedStatement ps= con.prepareStatement("select name from Users where name=? and password=?");
        ps.setString(2, password);
        ps.setString(1,username);
        ResultSet rs=ps.executeQuery();
        if(rs.next()) {
        	return true;
        }
    	return false;
    }
}
