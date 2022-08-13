package jdbc.servlets;

import jdbc.PageReader;
import jdbc.dataSets.User;
import jdbc.dbservice.UserService;
import jdbc.exceptions.WrongPasswordException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class SignInServlet extends HttpServlet {
    private final PageReader pageReader;
    private final UserService dbService;

    public SignInServlet(UserService dbService) {
        pageReader = new PageReader();
        this.dbService = dbService;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        String file = pageReader.readFile(new File("html_public", "signin.html"));
        response.getWriter().println(file);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            dbService.read(new User(login, password));
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("Authorized");
        } catch (WrongPasswordException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("Wrong password");
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("Unauthorized");
        }
        response.setContentType("text/html;charset=utf-8");
    }
}
