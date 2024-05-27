import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/servlet8")
public class servlet8 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
      PrintWriter  out = response.getWriter();
      response.setContentType("text/html");
      
       ServletContext context = getServletContext();
       Object obj = context.getAttribute("accno");
       String value = obj.toString();
       
       
       out.println("<html>");
       out.println("<body bgcolor=pink>"); 
       
       out.println("<center>");
       out.println("<h2>Ebank</h2>"); 
       out.println("<center>");
        
        out.println("<Form method=post action=servlet9>"); 
        out.println("<b> Click the Withdraw button</b>");
        
        out.println("<table>");
       
        out.println("<tr>");
        out.println("<td>");
        out.println("Account Number : " + value);
         out.println("</td>");
        out.println("</tr>");
        
         out.println("<tr>");
        out.println("<td>");
        out.println("Withdraw Amount : </td>  <td> <input type=text name=amount value=0>");
         out.println("</td>");
        out.println("</tr>");
        out.println("</table>");
        
         out.println("<input type=submit value=Withdeaw>");
         out.println("</br>");
         out.println("</Form>");
         out.println("</body>");
         out.println("</html>");
    }

}