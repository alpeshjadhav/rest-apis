package customUserService;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Map;

@Data
@RestController
@RequestMapping("/api/v1")
public class CustomUserController {

    private final CustomUserService userService;

    @GetMapping("users")
    public ResponseEntity<List<CustomUserDTO>> getUsers(){
        List<CustomUserDTO> users = userService.getUsers();
        if (users != null) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/users/list")
    public ResponseEntity<List<Map<String, String>>> getUsersList() {
        List<Map<String, String>> userList = userService.getUsersList();
        if (!userList.isEmpty()) {
            return ResponseEntity.ok(userList);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/users/report")
    public ResponseEntity<List<CustomUserDTO>> newGetUsersList() {
        List<CustomUserDTO> userList = userService.newGetUsers();
        if (!userList.isEmpty()) {
            return ResponseEntity.ok(userList);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/users/new/report")
    public ResponseEntity<List<CustomUserDTO>> getUsersList2() {
        List<CustomUserDTO> userList = userService.getUsers2();
        if (!userList.isEmpty()) {
            return ResponseEntity.ok(userList);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/users/{Id}")
    public ResponseEntity<CustomUserDTO> getUser(@PathVariable String Id){
        CustomUserDTO user = userService.getUser(Id);
        if(user != null){
            return new ResponseEntity<>(user,HttpStatus.OK);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/users/list/{Id}")
    public ResponseEntity<Map<String, String>> getUserById(@PathVariable String Id) {
        Map<String, String> user = userService.getUserById(Id);
        if (user != null && !user.isEmpty()) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @ControllerAdvice
    public static class RestResponseEntityExceptionHandler {
        @ExceptionHandler(value = { RestClientException.class })
        protected ResponseEntity<Object> handleRestClientException(RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
