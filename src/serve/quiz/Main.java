package serve.quiz;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Main", urlPatterns = {"/quiz/*"})
public class Main extends HttpServlet {
    private final static String INDEX_DIR = "/WEB-INF/quiz/main/";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quizId = getQuizId(request);

        request.setAttribute("id", quizId);

        request.setAttribute("main", INDEX_DIR + "main.jsp");
        request.setAttribute("head", INDEX_DIR + "head.jsp");
        request.setAttribute("scripts", List.of("/script/quiz/quizControl.js"));
        request.getRequestDispatcher("/WEB-INF/layouts/app.jsp").forward(request, response);
    }

    /**
     * Returns the id of the quiz based on request url
     */
    private int getQuizId(HttpServletRequest request) {
        String path[] = request.getRequestURI().split("/");
        try {
            return Integer.parseInt(path[2]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
