package mapper.example2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class UserMain {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(new File("src/main/resources/user.json"), User.class);
        System.out.println(user);

        JsonNode jsonNode = objectMapper.readTree(new File("src/main/resources/user.json"));;
        JsonNode address = jsonNode.get("address");
        System.out.println("\nUser address: "+address);

        String city = address.get("city").asText();
        System.out.println("\nUser City: "+city);

        String email = jsonNode.get("email").asText();
        System.out.println("\nUser Email: "+email);
    }
}
