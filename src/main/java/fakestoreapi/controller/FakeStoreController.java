package fakestoreapi.controller;

import fakestoreapi.dto.UsersWithCartsDTO;
import fakestoreapi.service.FakeStoreService;
import fakestoreapi.service.FakeStoreServiceWithJsonCore;
import fakestoreapi.service.FakeStoreStreamService;
import fakestoreapi.service.FakeStoreWebService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Data
@RestController
@RequestMapping("/api/v1")
public class FakeStoreController {

    private final FakeStoreService service;
    private final FakeStoreStreamService streamService;
    private final FakeStoreWebService storeWebService;
    private final FakeStoreServiceWithJsonCore newService;

    @GetMapping("/list1")
    public ResponseEntity<List<UsersWithCartsDTO>> getUsersWithCarts() {
        try {
            List<UsersWithCartsDTO> usersWithCarts = service.getUsersWithCarts();
            return ResponseEntity.ok(usersWithCarts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/users1")
    public ResponseEntity<List<UsersWithCartsDTO>> getUsersWithCartsNew() {
        try {
            List<UsersWithCartsDTO> usersWithCarts = streamService.getUsersWithCartsNew();
            return ResponseEntity.ok(usersWithCarts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/users2")
    public ResponseEntity<List<UsersWithCartsDTO>> getUsersWithCartsNew1() {
        try {
            List<UsersWithCartsDTO> usersWithCarts = storeWebService.getUsersWithCartsNew1();
            return ResponseEntity.ok(usersWithCarts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/list2")
    public ResponseEntity<List<UsersWithCartsDTO>> getUsersWithCartsList() {
        try {
            List<UsersWithCartsDTO> usersWithCarts = newService.getUsersWithCartsList();
            return ResponseEntity.ok(usersWithCarts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
