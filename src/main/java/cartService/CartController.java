package cartService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<String> getCarts(){
        try{
            log.info("===========>>>> Getting Carts <<<<============");
            String carts = cartService.getCarts();
            return new ResponseEntity<>(carts,HttpStatus.OK);
        }catch (Exception e){
            log.info("===========>>>> INTERNAL_SERVER_ERROR for Getting All Carts <<<<============");
            return new ResponseEntity<>("Error fetching carts", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
