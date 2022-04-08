package co.edu.unbosque.tiendadeartevale.UserService;


import java.io.*;
import java.util.List;

import co.edu.unbosque.tiendadeartevale.DTOS.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

    @WebServlet(name = "login", value = "/login")
    public class LogInServlet extends HttpServlet {

        public void init() {}

        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            response.setContentType("text/html");

            String username = request.getParameter("UserName");
            String password = request.getParameter("Password");

            List<User> users = new UserService().getUsers();

            User userFounded = users.stream()
                    .filter(user -> username.equals(user.getUsername()) && password.equals(user.getPassword()))
                    .findFirst()
                    .orElse(null);

            if (userFounded != null) {
                request.setAttribute("role", userFounded.getRole());

                Cookie cookie = new Cookie("role", userFounded.getRole());
                cookie.setMaxAge(20);
                response.addCookie(cookie);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("./index.html");
                dispatcher.forward(request, response);

            } else {
                response.sendRedirect("./401.html");
            }
        }

        public void destroy() {}
    }


