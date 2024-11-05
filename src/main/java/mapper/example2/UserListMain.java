package mapper.example2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UserListMain {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<User> users = objectMapper.readValue(new File("src/main/resources/userList.json"), new TypeReference<>() {
        });

        users.forEach(System.out::println);

        System.out.println("\nUsers who have city : kilcoole");
        users.stream()
                .filter(user -> "kilcoole".equalsIgnoreCase(user.getAddress().getCity()))
                .forEach(System.out::println);
    }
}
