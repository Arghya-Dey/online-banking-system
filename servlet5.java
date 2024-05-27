
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/servlet5")
public class servlet5 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection con;
            PreparedStatement pst;
            ResultSet rs;
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            String name = request.getParameter("name");
            String accno = request.getParameter("accno1");
            String pinno = request.getParameter("pinno1");

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/bankingsystem1", "root", "");

            pst = con.prepareStatement("select * from login where accno = ? and pinno = ?");
            pst.setString(1, accno);
            pst.setString(2, pinno);
            rs = pst.executeQuery();
            boolean row = false;
            row = rs.next();

            if (row == false) {

                pst = con.prepareStatement("insert into login(name,accno,pinno)values(?,?,?)");

                pst.setString(1, name);
                pst.setString(2, accno);
                pst.setString(3, pinno);

                int rows = pst.executeUpdate();

                if (rows == 1) {

                    out.print("<html>");
                    out.print("<body bgcolor=pink>");

                    out.print("<div>");
                    out.print("Your account has been created");
                    out.print("</div>");

                    out.print("<div>");
                    out.println("<Form action=\"index.html\">");
                    out.println("<input type=\"submit\" value=Login>");
                    out.println("</Form>");
                    out.print("</div>");

                    out.print("</body");
                    out.print("</html");
                }
            } else {

                out.print("<html>");
                out.print("<body bgcolor=pink>");

                out.print("<div>");
                out.print("Account already exist");
                out.print("</div>");

                out.print("<div>");
                out.println("<Form action=\"index.html\">");
                out.println("<input type=\"submit\" value=Login>");
                out.println("</Form>");
                out.print("</div>");

                out.print("</body");
                out.print("</html");
            }

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
