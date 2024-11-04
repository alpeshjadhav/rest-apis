package reqres.in.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reqres.in.model.User;
import reqres.in.model.UserResponse;
import reqres.in.model.UsersListResponse;
import reqres.in.service.UserService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsersFromApi();
    }

    @GetMapping("/users/{id}")
    public Optional<UserResponse> getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }
}
