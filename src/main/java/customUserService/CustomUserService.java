package customUserService;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@Service
public class CustomUserService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl = "https://dummyjson.com/users";

    public List<CustomUserDTO> getUsers() {
        List<CustomUserDTO> userList = new ArrayList<>();
        // Fetching user API data
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            try {
                JSONObject jsonResponse = new JSONObject(responseEntity.getBody());
                JSONArray usersArray = jsonResponse.getJSONArray("users");
                for (int i = 0; i < usersArray.length(); i++) {
                    JSONObject user = usersArray.getJSONObject(i);

                    // Extracting custom fields from the user object
                    String id = String.valueOf(user.getInt("id"));
                    String firstName = user.getString("firstName");
                    String lastName = user.getString("lastName");
                    String email = user.getString("email");

                    // Extracting address fields
                    JSONObject address = user.getJSONObject("address");
                    String street = address.getString("address");
                    String city = address.getString("city");
                    String state = address.getString("state");
                    String postalCode = address.getString("postalCode");

                    // Creating CustomUserDTO object
                    CustomUserDTO customUserDTO = new CustomUserDTO();
                    customUserDTO.setId(id);
                    customUserDTO.setFirstName(firstName);
                    customUserDTO.setLastName(lastName);
                    customUserDTO.setEmail(email);
                    customUserDTO.setAddress(street);
                    customUserDTO.setCity(city);
                    customUserDTO.setState(state);
                    customUserDTO.setPostalCode(postalCode);
                    userList.add(customUserDTO);
                }
                return userList;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        // If there's an error or no user data retrieved, return an empty list
        return userList;
    }

    public List<CustomUserDTO> newGetUsers() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            return new ArrayList<>();
        }

        try {
            JSONObject jsonResponse = new JSONObject(responseEntity.getBody());
            JSONArray usersArray = jsonResponse.getJSONArray("users");

            return IntStream.range(0, usersArray.length())
                    .mapToObj(i -> usersArray.getJSONObject(i))
                    .map(user -> {
                        // Extracting custom fields from the user object
                        String id = String.valueOf(user.getInt("id"));
                        String firstName = user.getString("firstName");
                        String lastName = user.getString("lastName");
                        String email = user.getString("email");

                        // Extracting address fields
                        JSONObject address = user.getJSONObject("address");
                        String street = address.getString("address");
                        String city = address.getString("city");
                        String state = address.getString("state");
                        String postalCode = address.getString("postalCode");

                        // Creating CustomUserDTO object
                        CustomUserDTO customUserDTO = new CustomUserDTO();
                        customUserDTO.setId(id);
                        customUserDTO.setFirstName(firstName);
                        customUserDTO.setLastName(lastName);
                        customUserDTO.setEmail(email);
                        customUserDTO.setAddress(street);
                        customUserDTO.setCity(city);
                        customUserDTO.setState(state);
                        customUserDTO.setPostalCode(postalCode);
                        return customUserDTO;
                    })
                    .collect(Collectors.toList());
        } catch (JSONException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<CustomUserDTO> getUsers2() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            return List.of();  // Immutable empty list
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> responseMap = objectMapper.readValue(responseEntity.getBody(), Map.class);
            List<Map<String, Object>> usersList = (List<Map<String, Object>>) responseMap.get("users");

            return usersList.stream()
                    .map(user -> {
                        String id = String.valueOf(user.get("id"));
                        String firstName = (String) user.get("firstName");
                        String lastName = (String) user.get("lastName");
                        String email = (String) user.get("email");

                        Map<String, String> address = (Map<String, String>) user.get("address");
                        String street = address.get("address");
                        String city = address.get("city");
                        String state = address.get("state");
                        String postalCode = address.get("postalCode");

                        return new CustomUserDTO(id, firstName, lastName, email, street, city, state, postalCode);
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();  // Immutable empty list
        }
    }

    public List<Map<String, String>> getUsersList() {
        List<Map<String, String>> userList = new ArrayList<>();
        // Fetching user API data
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            try {
                JSONObject jsonResponse = new JSONObject(responseEntity.getBody());
                JSONArray usersArray = jsonResponse.getJSONArray("users");
                for (int i = 0; i < usersArray.length(); i++) {
                    JSONObject user = usersArray.getJSONObject(i);
                    // Create a new map to store key-value pairs for each user

                    Map<String, String> userData = new LinkedHashMap<>();
                    // Extracting and adding custom fields from the user object
                    userData.put("id", String.valueOf(user.getInt("id")));
                    userData.put("firstName", user.getString("firstName"));
                    userData.put("lastName", user.getString("lastName"));
                    userData.put("username", user.getString("username"));
                    userData.put("password", user.getString("password"));
                    userData.put("email", user.getString("email"));
                    userData.put("phone", user.getString("phone"));
                    userData.put("image", user.getString("image"));

                    // Extracting and adding address fields
                    JSONObject address = user.getJSONObject("address");
                    userData.put("street", address.getString("address"));
                    userData.put("city", address.getString("city"));
                    userData.put("state", address.getString("state"));
                    userData.put("postalCode", address.getString("postalCode"));

                    // Add the map containing user data to the userList
                    userList.add(userData);
                }
                return userList;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        // If there's an error or no user data retrieved, return an empty list
        return userList;
    }

    public CustomUserDTO getUser(String Id) {

        String url= "https://dummyjson.com/users/"+Id;
        // Fetching user API data
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            try {
                JSONObject user = new JSONObject(responseEntity.getBody());

                    // Extracting custom fields from the user object
                    String id = String.valueOf(user.getInt("id"));
                    String firstName = user.getString("firstName");
                    String lastName = user.getString("lastName");
                    String email = user.getString("email");

                    // Extracting address fields
                    JSONObject address = user.getJSONObject("address");
                    String street = address.getString("address");
                    String city = address.getString("city");
                    String state = address.getString("state");
                    String postalCode = address.getString("postalCode");

                    // Creating CustomUserDTO object
                    CustomUserDTO customUserDTO = new CustomUserDTO();
                    customUserDTO.setId(id);
                    customUserDTO.setFirstName(firstName);
                    customUserDTO.setLastName(lastName);
                    customUserDTO.setEmail(email);
                    customUserDTO.setAddress(street);
                    customUserDTO.setCity(city);
                    customUserDTO.setState(state);
                    customUserDTO.setPostalCode(postalCode);

                return customUserDTO;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Map<String, String> getUserById(String Id) {

        String url= "https://dummyjson.com/users/"+Id;
        // Fetching user API data
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            try {
                JSONObject user = new JSONObject(responseEntity.getBody());

                Map<String, String> userData = new LinkedHashMap<>();
                // Extracting and adding custom fields from the user object
                userData.put("id", String.valueOf(user.getInt("id")));
                userData.put("firstName", user.getString("firstName"));
                userData.put("lastName", user.getString("lastName"));
                userData.put("username", user.getString("username"));
                userData.put("password", user.getString("password"));
                userData.put("email", user.getString("email"));
                userData.put("phone", user.getString("phone"));
                userData.put("image", user.getString("image"));

                // Extracting and adding address fields
                JSONObject address = user.getJSONObject("address");
                userData.put("street", address.getString("address"));
                userData.put("city", address.getString("city"));
                userData.put("state", address.getString("state"));
                userData.put("postalCode", address.getString("postalCode"));

                return  userData;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
