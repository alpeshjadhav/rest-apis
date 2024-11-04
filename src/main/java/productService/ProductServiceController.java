package productService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductServiceController {

    private final ProductService productService;
    private final CustomeProductService service;

    @GetMapping("")
    public ResponseEntity<String> getProducts(){
        try{
            log.info("===========>>>> Getting All Products <<<<============");
            String products = productService.getProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        }catch (Exception e){
            log.info("===========>>>> INTERNAL_SERVER_ERROR for Getting All Products <<<<============");
            return new ResponseEntity<>("Error fetching users", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Map<String, Object>>> getProductsList() {
        try {
            List<Map<String, Object>> products = productService.getCustomizedProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/new-list")
    public ResponseEntity<List<Map<String, Object>>> getProductsNewList() {
        try {
            List<Map<String, Object>> products = service.getProductsList();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/get-all")
    public ResponseEntity<String> getALLProducts(){
        try{
            log.info("===========>>>> Getting All Products <<<<============");
            String products = productService.getAllProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        }catch (Exception e){
            log.info("===========>>>> INTERNAL_SERVER_ERROR for Getting All Products <<<<============");
            return new ResponseEntity<>("Error fetching products", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable int id){
        try{
            log.info("===========>>>> Getting Product <<<<============");
            String product = productService.getProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch (Exception e){
            log.info("===========>>>> INTERNAL_SERVER_ERROR for Getting Product <<<<============");
            return new ResponseEntity<>("Error fetching users", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
