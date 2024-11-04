package fakestoreapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private int productId;
    private int quantity;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
