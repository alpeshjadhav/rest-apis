package userService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserServiceController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<String> getUsers() {
        try {
            String users = userService.getUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching users");
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<String> getAllUsers() {
        try {
            String users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching users");
        }
    }

    @GetMapping("/fetch")
    public ResponseEntity<List<User>> fetchUsers() throws IOException {
        List<User> users= userService.fetchUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/new/fetch")
    public ResponseEntity<List<User>> getUsersNew() throws IOException {
        List<User> users= userService.getUsersNew();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getUserById(@PathVariable int id) {
        try {
            String userData = userService.getUserById(id);
            return ResponseEntity.ok(userData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/{id}/carts")
    public ResponseEntity<String> getCartById(@PathVariable int id) {
        try {
            String userCartData = userService.getCartById(id);
            return ResponseEntity.ok(userCartData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found");
        }
    }
}
