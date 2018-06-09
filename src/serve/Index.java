package serve;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

@javax.servlet.annotation.WebServlet(name = "Index", urlPatterns = {"/index"})
public class Index extends HttpServlet {
    private final static String INDEX_DIR = "/WEB-INF/home/";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("main", INDEX_DIR + "main.jsp");
        request.setAttribute("head", INDEX_DIR + "head.jsp");
        request.getRequestDispatcher("WEB-INF/layouts/app.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
