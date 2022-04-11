package co.edu.unbosque.tiendadeartevale;

import co.edu.unbosque.tiendadeartevale.DTOS.User;
import co.edu.unbosque.tiendadeartevale.UserService.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "recargarFCoins", value = "/recargarFCoins")
public class FCoinsServlet extends HttpServlet {

    public void init(){}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String money = request.getParameter("money");

        List<User> users = new UserService().getUsers();
        User usuario = null;

        for (User user : users){
            if (user.getUsername().equalsIgnoreCase(username)){
                usuario = user;
                users.remove(usuario);
                break;
            }
        }

        String currentMoney = usuario.getMoney();
        String newMoney = String.valueOf(Integer.parseInt(currentMoney) + Integer.parseInt(money));
        usuario.setMoney(newMoney);
        new UserService().createUser(usuario.getUsername(), usuario.getPassword(), usuario.getRole(), usuario.getMoney(), getServletContext().getRealPath("") + File.separator);

        request.setAttribute("role", usuario.getRole());
        request.setAttribute("password", usuario.getPassword());
        request.setAttribute("money", usuario.getMoney());
        request.setAttribute("username", usuario.getUsername());

        if (usuario.getRole().equals("artista")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("./colecciones_U.html");
            dispatcher.forward(request, response);
        } else if (usuario.getRole().equals("comprador")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("./verPiezas.html");
            dispatcher.forward(request, response);
        }

    }

    public void destroy() {}
}
