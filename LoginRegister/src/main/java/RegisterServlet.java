import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.tomcat.util.log.SystemLogHandler;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	PrintWriter out =response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        HttpSession session = request.getSession();
        try {
			boolean result= registerUser(username,password,email);
			if(result) {
			session.setAttribute("username", username);
			session.setAttribute("message", "Registration successful");
			response.sendRedirect("jsp/welcome.jsp");
			}
			else {
			session.setAttribute("message","Registration unsuccessful");
			response.sendRedirect("jsp/login.jsp");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			session.setAttribute("message","Exception occurred");
			response.sendRedirect("jsp/login.jsp");
		}
    }
    private boolean  registerUser(String uname,String password,String email) throws ClassNotFoundException, SQLException {
    	Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/auth","shrikant","Shrikant@123");
        PreparedStatement ps= con.prepareStatement("INSERT INTO Users (name, password,email) VALUES (?, ?, ?)");
        ps.setString(2, password);
        ps.setString(1,uname);
        ps.setString(3, email);
        int rowsAffected = ps.executeUpdate();
        // Check if any rows were affected to determine if the insertion was successful
        boolean isSuccess = (rowsAffected > 0);
        System.out.println(isSuccess);
        return isSuccess;
    }
}
