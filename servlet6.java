import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Statement;
import jakarta.servlet.RequestDispatcher;
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

@WebServlet("/servlet6")
public class servlet6 extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection con;
            PreparedStatement pst;
            ResultSet rs;
            ResultSet rs2;
            ResultSet rs3;
            Statement stmt;
            
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            
            ServletContext context = getServletContext();
            
            String accno = request.getParameter("accno");
            String pinno = request.getParameter("pinno");
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/onlinebanking", "root", "");
            pst = con.prepareStatement("select * from login where accno = ? and pinno = ?");
            pst.setString(1, accno);
            pst.setString(2, pinno);
            rs = pst.executeQuery();
            boolean row = false;
            row = rs.next();
            
            if (row == true) {
                String name;
                name = rs.getString(2);
                pst = con.prepareStatement("SELECT SUM(ALL mdeposit) AS total_deposit FROM account_holder WHERE accnum=? AND statement=\"Deposit\"");
                pst.setString(1, accno);
                rs = pst.executeQuery();
                Integer deposit=0;
                rs.next();
                deposit=rs.getInt(1);
                
                pst = con.prepareStatement("SELECT SUM(ALL mdeposit) AS total_withdraw FROM account_holder WHERE accnum=? AND statement=\"Withdraw\"");
                pst.setString(1, accno);
                rs2 = pst.executeQuery();
                Integer withdraw=0;
                rs2.next();
                withdraw=rs2.getInt(1);
                
                Integer balance=0;
                balance=deposit-withdraw;
                
                pst = con.prepareStatement("Select date,statement,mdeposit from account_holder where accnum=?");
                pst.setString(1,accno);
                
                
                
                
                out.print("<table>");                                
                out.println("<html>");
                out.println("<body bgcolor=pink>");
                
                out.println("<center>");
                out.println("<h2>Ebank</h2>");
                out.println("<center>");
                
                out.println("<Form>");
                out.println("<b><u> Account Statement </u></b>");
                
                out.println("<table align=center>");
                
                out.println("<tr>");
                out.println("<td>");
                out.println("Account Holder Name : ");
                out.println("</td>");
                
                out.println("<td>");
                out.print(name);
                out.println("</td>");
                
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td>");
                out.println("Account Number : ");
                out.println("</td>");
                
                out.println("<td>");
                out.print(accno);
                out.println("</td>");
                
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td>");
                out.println("Account Balance :");
                out.println("</td>");
                
                out.println("<td>");
                out.print(balance);
                out.println("</td>");
                
                out.println("</tr>");
                out.println("</table>");
                
                out.println("</br>");
                out.println("</Form>");
                
                out.println("<Form action=\"index.html\">");
                
                out.println("<input type=\"submit\" value=Logout>");
                
                out.println("</Form>");
                
                out.println("<Form action=\"Deposit.html\">");
                
                out.println("<input type=\"submit\" value=Deposit>");
                
                out.println("</Form>");
                
                out.println("<Form action=\"withdraw.html\">");
                
                out.println("<input type=\"submit\" value=Withdraw>");
                
                out.println("</Form>");
                
                out.print("<table width=75% border=1 align=center>");
                    
                    rs3=pst.executeQuery();

                    out.print("<tr>");
                    out.print("<th>Date</th>");
                    out.print("<th>Statement</th>");
                    out.print("<th>Amount</th>");
    
                    out.print("</tr>");
                    while(rs3.next()){
                        out.print("<tr align=center><td>"+rs3.getDate(1)+"</td><td>"+rs3.getString(2)+"</td><td>"+rs3.getInt(3)+"</td></tr>");
                    }
                    out.print("</table>");
                
                out.println("</body>");
                out.println("</html>");
            } else {
                out.println("<html>");
                out.println("<body bgcolor=pink>");
                out.println("Account not exist!");
                out.println("</body");
                out.println("</html");
            }
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}