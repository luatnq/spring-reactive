package dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO implements Serializable {
    private String id;

    private String uid;

    @JsonProperty("product_name")
    @Size(min = 1, max = 100, message = "Product name is from 1 to 100 characters")
    private String productName;

    @JsonProperty("publisher_name")
    @Size(min = 1, max = 100, message = "Publisher name is from 1 to 100 characters")
    private String publisherName;

    @JsonProperty("price")
    private long price;

    @JsonProperty("description")
    @Size(min = 1, max = 200, message = "Description is from 1 to 200 characters")
    private String description;

    @JsonProperty("image_url")
    private String imageURL;


    @JsonProperty("created_date")
    @CreatedDate
    @JsonIgnore
    private LocalDateTime createdDate = LocalDateTime.now();

    @JsonProperty("updated_date")
    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime updatedDate ;

//    public ProductDTO(Product u, Product r) {
//    }
}
