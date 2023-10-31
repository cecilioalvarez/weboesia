package es.oesia;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HolaSQL
 */
public class HolaSQL extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public HolaSQL() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://mi_mysql:3306/wordpress";
			Connection conn = DriverManager.getConnection(url, "root", "cecilio");
			Statement stmt = conn.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery(" select user_email from wp_users;");
			while (rs.next()) {
				String nombre = rs.getString("user_email");
				out.println(nombre);
			}
			conn.close();
		} catch (Exception e) {
			out.println("Got an exception! no hay driver ");
			out.println(e.getMessage());
		}

	}

}
