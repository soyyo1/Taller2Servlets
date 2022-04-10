package co.edu.unbosque.tiendadeartevale.UserService;

import co.edu.unbosque.tiendadeartevale.DTOS.NFT;
import co.edu.unbosque.tiendadeartevale.DTOS.User;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

public class NFTService {

    public List<NFT> get() throws IOException {
        List<NFT> nfts;
        try (InputStream is = UserService.class.getClassLoader()
                .getResourceAsStream("nfts.csv")) {

            HeaderColumnNameMappingStrategy<NFT> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(NFT.class);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

                CsvToBean<NFT> csvToBean = new CsvToBeanBuilder<NFT>(br)
                        .withType(NFT.class)
                        .withMappingStrategy(strategy)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                nfts = csvToBean.parse();
            }
        }

        return nfts;
    }

    public void createNFT(String titulo, String coins, String nombreArchivo, String path) throws IOException {
        String newLine = "\n" + titulo + "," + coins + "," + nombreArchivo;
        FileOutputStream os = new FileOutputStream(path + "WEB-INF/classes/" + "nfts.csv", true);
        os.write(newLine.getBytes());
        os.close();
    }

    public String generarTextoAleatorio(int longitud) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < longitud; i++) {
            char character = (char) (random.nextInt(26));
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }
}
