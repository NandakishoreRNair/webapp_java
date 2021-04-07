package com.serve;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/Dataservlet")
public class Dataservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {Class.forName("com.mysql.jdbc.Driver");
			response.setContentType("text/html");
        
		String  brand=request.getParameter("brand");
		String basics=request.getParameter("basic");
		String fspec=request.getParameter("full");
		
		
			java.sql.Connection con= DriverManager.getConnection(
			        "jdbc:mysql://localhost:3306/mobile","root","password");
			
			 PreparedStatement ps = con.prepareStatement("select id,company,"+basics+",ram,"+fspec+" from mobilefull where company='"+brand+"'");
			 ResultSet rs= ps.executeQuery();
			 PrintWriter out = response.getWriter();
			 out.print("<html><body>Query Result<table border='1'><tr><th>id</th><th>company</th><th>"+basics+"</th><th>ram</th><th>"+fspec+"</th></tr>");
	            while(rs.next()) 
	                {System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)
	                		+"  "+rs.getString(4)+"  "+rs.getString(5));
	            
	                out.print("<tr>");
	                out.print("<td>"+rs.getInt(1)+"</td>");
	                out.print("<td>"+rs.getString(2)+"</td>");
	                out.print("<td>"+rs.getString(3)+"</td>");
	                out.print("<td>"+rs.getString(4)+"</td>");
	                out.print("<td>"+rs.getString(5)+"</td>");
	                out.print("</tr>");
		            }
		            out.print("</table></body></html>");
	            con.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
