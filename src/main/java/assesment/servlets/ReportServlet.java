package assesment.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "ReportServlet", urlPatterns = "/reportPage")
public class ReportServlet extends HttpServlet {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/inventory?allowPublicKeyRetrieval=true&useSSL=false";

    //  Database credentials
    static final String USER = "root";
    static final String PASSWORD = "qwerty123";

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        PrintWriter out = res.getWriter();
        try {

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            res.setContentType("text/html");
            out.println("<html><body>");
            try {
                Class.forName(JDBC_DRIVER);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM inventory.inventory_management order by productName asc; ");
                out.println("<table border=1 width=50% height=50%>");
                out.println("<tr><th>EmpId</th><th>EmpName</th><th>Salary</th><tr>");
                while (rs.next()) {
                    //Retrieve by column name
                    int productId = rs.getInt("productId");
                    String productName = rs.getString("productName");
                    double costPrice = rs.getFloat("costPrice");
                    double sellingPrice = rs.getFloat("sellingPrice");
                    out.println("<tr><td>" + productId + "</td><td>" + productName + "</td><td>" + costPrice + "</td><td>" + sellingPrice + "</td></tr>");
                }
                out.println("</table>");
                out.println("</html></body>");
                conn.close();
            } catch (Exception e) {
                out.println("error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




