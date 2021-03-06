package co.edu.unbosque.tiendadeartevale.UserService;

import co.edu.unbosque.tiendadeartevale.DTOS.User;
import com.opencsv.bean.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

public class UserService {

    public List<User> getUsers() throws IOException {

        List<User> users;

        try (InputStream is = UserService.class.getClassLoader()
                .getResourceAsStream("users.csv")) {

            HeaderColumnNameMappingStrategy<User> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(User.class);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

                CsvToBean<User> csvToBean = new CsvToBeanBuilder<User>(br)
                        .withType(User.class)
                        .withMappingStrategy(strategy)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                users = csvToBean.parse();
            }
        }

        return users;
    }

    public void createUser(String username, String password, String role, String money, String path) throws IOException {
        String newLine = "\n" + username + "," + password + "," + role + "," + money + "\n";
        FileOutputStream os = new FileOutputStream(path + "WEB-INF/classes/" + "users.csv", true);
        os.write(newLine.getBytes());
        os.close();
    }
}