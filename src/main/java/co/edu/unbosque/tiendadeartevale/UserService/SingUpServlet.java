package co.edu.unbosque.tiendadeartevale.UserService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

    @WebServlet(name = "signup", value = "/signup")
    public class SingUpServlet extends HttpServlet {

        public void init() {}

        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            try {
                new UserService().createUser(username, password, getServletContext().getRealPath("") + File.separator);
            } catch (Exception e) {
                e.printStackTrace();
            }

            response.sendRedirect("./index.html");
        }

        public void destroy() {}
    }