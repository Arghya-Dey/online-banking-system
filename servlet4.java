
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/servlet4")
public class servlet4 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get PrintWriter
        PrintWriter pw = response.getWriter();
        //set content type
        response.setContentType("text/html");
        //read the from values
        String accno = request.getParameter("accno1");
        String pinno = request.getParameter("pinno1");

        System.out.println("AccNo: " + accno);
        System.out.println("pinNo: " + pinno);

        //load the jdbc driver
        try {
            //  Block of code to try
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            //  Block of code to handle errors
            e.printStackTrace();
        }
        //close the stream
        pw.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

}
