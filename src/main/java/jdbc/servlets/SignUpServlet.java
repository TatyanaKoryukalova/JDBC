package jdbc.servlets;

import jdbc.PageReader;
import jdbc.dataSets.User;
import jdbc.dbservice.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class SignUpServlet extends HttpServlet {
    private final PageReader pageReader;
    private final UserService dbService;

    public SignUpServlet(UserService dbService) {
        pageReader = new PageReader();
        this.dbService = dbService;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        String file = pageReader.readFile(new File("html_public", "signup.html"));
        response.getWriter().println(file);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try {
            int id = dbService.create(new User(login, password));
            response.getWriter().println("Created entity with id " + id);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            response.getWriter().println("Login already exists" + "\n" + e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        response.setContentType("text/html;charset=utf-8");
    }
}
