
import jakarta.servlet.ServletContext;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/servlet9")
public class servlet9 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection con;
            PreparedStatement pst;
            ResultSet rs;

            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/bankingsystem1", "root", "");
            ServletContext context = getServletContext();
            Object obj = context.getAttribute("accno");
            String accno = obj.toString();

            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            String date = df.format(now);
            String amount = request.getParameter("amount");

            String statement = "Withdraw";

            pst = con.prepareStatement("insert into account_holder(accnum,date,statement,mdeposit)values(?,?,?,?)");
            pst.setString(1, accno);
            pst.setString(2, date);
            pst.setString(3, statement);
            pst.setString(4, amount);

            int rows = pst.executeUpdate();

            if (rows == 1) {

                out.print("<html>");
                out.print("<body bgcolor=pink>");

                out.print("<div>");
                out.print("Your Transaction have been done");
                out.print("</div>");

                out.print("<div>");
                out.println("<Form action=\"index.html\">");
                out.println("<input type=\"submit\" value=Logout>");
                out.println("</Form>");
                out.print("</div>");

                out.print("</body");
                out.print("</html");
            } else {

                out.print("<html>");
                out.print("<body bgcolor=pink>");
                out.print("Your Transaction failed");
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
