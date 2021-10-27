package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/member/list")
public class MemberListServlet extends GenericServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		
		Connection conn = null;
		Statement stmt  = null;
		ResultSet rs 	= null;
		String url = "jdbc:mariadb://127.0.0.1:3306/firejava";
		
		try {
			 Class.forName("org.mariadb.jdbc.Driver");
			 conn = DriverManager.getConnection(url,"root","1234");
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery(
					 				"select MNO,MNAME,EMAIL,CRE_DATE" +
					 			    " from MEMBERS " + " order by MNO ASC");
			res.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = res.getWriter();
			out.println("<html><head><title>회원목록</title></head>");
			out.println("<body><h1>회원목록</h1>");
			out.println("<p><a href='add'>신규 회원</a></p>");
			
			
			
			while(rs.next()) {
				out.println(
								rs.getInt("MNO") + "," +
								rs.getString("MNO") + "," + 
								rs.getString("MNAME") + "," + 
								rs.getString("EMAIL") + "," + 
								rs.getDate("CRE_DATE") + "<br>" 
						    );
			}
			
			
			out.println("</body></html>");
			
			
			
			
		} catch (Exception e) {
			throw new ServletException();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if(stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
	}
}
