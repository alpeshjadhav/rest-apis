package reqres.in.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reqres.in.model.User;
import reqres.in.model.UserResponse;
import reqres.in.model.UsersListResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {

    private final RestTemplate restTemplate;

    public List<User> getUsersFromApi() {
        String url = "https://reqres.in/api/users";
        UsersListResponse response = restTemplate.getForObject(url, UsersListResponse.class);
        return response.getData()
                .stream()
                .collect(Collectors.toList());
    }

    public Optional<UserResponse> getUserById(int id) {
        String url = "https://reqres.in/api/users/" + id;
        UserResponse response = restTemplate.getForObject(url, UserResponse.class);
        return Optional.ofNullable(response);
    }
}
