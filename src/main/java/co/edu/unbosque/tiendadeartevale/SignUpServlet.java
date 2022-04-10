package co.edu.unbosque.tiendadeartevale;
import co.edu.unbosque.tiendadeartevale.UserService.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

    @WebServlet(name = "signup", value = "/signup")
    public class SignUpServlet extends HttpServlet {

        public void init() {}
// metodo para crear un nuevo usuario
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            String money = String.valueOf(0);

            try {
                new UserService().createUser(username, password, role, money,getServletContext().getRealPath("") + File.separator);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (role.equals("artista")){
                RequestDispatcher dispatcher = request.getRequestDispatcher("./perfilArtista.html");
                dispatcher.forward(request, response);
                response.sendRedirect("./perfilArtista.html");
                System.out.println("entro");
            }
            else if (role.equalsIgnoreCase("comprador")){
                RequestDispatcher dispatcher = request.getRequestDispatcher("./perfilComprador.html");
                dispatcher.forward(request, response);
            }
        }

        public void destroy() {}
    }