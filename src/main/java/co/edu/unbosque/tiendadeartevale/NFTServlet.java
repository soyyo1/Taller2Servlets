package co.edu.unbosque.tiendadeartevale;

import co.edu.unbosque.tiendadeartevale.UserService.NFTService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "subir-nft", value = "/subir-nft")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class NFTServlet extends HttpServlet {
    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String titulo = request.getParameter("titulo");
        String coins = request.getParameter("coins");

        String pathFolder = getServletContext().getRealPath("") + File.separator + "images";
        File file = new File(pathFolder);

        if (!(file.exists())) {
            file.mkdir();
        }

        NFTService nftService = new NFTService();

        String nombreArchivo = null;
        for (Part part : request.getParts()) {
            nombreArchivo = nftService.generarTextoAleatorio(8) + part.getSubmittedFileName();
            part.write(pathFolder + File.separator + nombreArchivo);
        }

        try {
            new NFTService().createNFT(titulo, coins, nombreArchivo, getServletContext().getRealPath("") + File.separator);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}