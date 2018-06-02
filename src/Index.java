import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

@javax.servlet.annotation.WebServlet(name = "Index", urlPatterns = {"/index"})
public class Index extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
