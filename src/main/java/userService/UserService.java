package userService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class UserService {

    private final RestTemplate restTemplate;
    private static final String API_URL = "https://dummyjson.com/users";

    public String getUsers() {
        return restTemplate.getForObject(API_URL, String.class);
    }

    public List<User> fetchUsers() throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(connection.getInputStream());
        JsonNode usersNode = rootNode.path("users");

        return StreamSupport.stream(usersNode.spliterator(), false)
                .map(userNode -> {
                    return User.builder()
                            .id(userNode.path("id").asInt())
                            .firstName(userNode.path("firstName").asText())
                            .lastName(userNode.path("lastName").asText())
                            .email(userNode.path("email").asText())
                            .phone(userNode.path("phone").asText())
                            .ip(userNode.path("ip").asText())
                            .city(userNode.path("address").path("city").asText())
                            .title(userNode.path("company").path("title").asText())
                            .department(userNode.path("company").path("department").asText())
                            .role(userNode.path("role").asText())
                            .build();
                })
                .collect(Collectors.toList());
    }

    public List<User> getUsersNew() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(API_URL, String.class);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            return List.of();  // Immutable empty list
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> responseMap = objectMapper.readValue(responseEntity.getBody(), Map.class);
            List<Map<String, Object>> usersList = (List<Map<String, Object>>) responseMap.get("users");

            return usersList.stream()
                    .map(user -> {
                        Integer id = (Integer) user.get("id");
                        String firstName = (String) user.get("firstName");
                        String lastName = (String) user.get("lastName");
                        String email = (String) user.get("email");
                        String phone = (String) user.get("phone");
                        String ip = (String) user.get("ip");
                        String role = (String) user.get("role");

                        Map<String, String> address = (Map<String, String>) user.get("address");
                        String city = address.get("city");

                        Map<String, String> company = (Map<String, String>) user.get("company");
                        String department = company.get("department");
                        String title = company.get("title");

                        return new User(id, firstName, lastName, email,phone, ip, city, title, department, role);
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();  // Immutable empty list
        }
    }


    public String getAllUsers(){
        //SET HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        //SET HttpEntity
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        //RETURN VALUE
        return restTemplate.exchange(API_URL, HttpMethod.GET, entity, String.class)
                .getBody();
    }

    public String getUserById(int id) {
        String GET_USER_URL = API_URL + "/" + id;
        return restTemplate.getForObject(GET_USER_URL, String.class);
    }

    public String getCartById(int id) {
        String GET_CART_URL = API_URL + "/" + id + "/carts";
        return restTemplate.getForObject(GET_CART_URL, String.class);
    }
}
