package pk.modelDto;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {
    private Long id;
    private String firstName;
    private String lastName;
    private List<Long> productsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Long> getProductsId() {
        return productsId;
    }

    public void setProductsId(List<Long> products) {
        this.productsId = productsId;
    }

    public void addProductDto(Long productId) {
        if (this.productsId == null) {
            this.productsId = new ArrayList<>();
        }
        this.productsId.add(productId);
    }

}
