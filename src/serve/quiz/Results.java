package serve.quiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Results", urlPatterns = "/quiz/results")
public class Results extends HttpServlet {
    private final static String INDEX_DIR = "/WEB-INF/quiz/results/";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("main", INDEX_DIR + "main.jsp");
        request.getRequestDispatcher("/WEB-INF/layouts/app.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
