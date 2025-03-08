import org.felfeit.model.Product;
import org.felfeit.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductServiceTest {
    private ProductService productService;

    @BeforeEach
    public void setup() {
        productService = new ProductService();
    }

    @Test
    private void testAddProduct() {
        Product product = new Product();
        product.setName("Laptop ASUS");
        product.setCategoryId(1);
        product.setStock(10);
        product.setPurchasePrice(8000000);
        product.setSellingPrice(10000000);
        product.setDescription("Laptop ASUS ROG");
        product.setSupplierId(1);

        productService.addProduct(product);
        assertNotNull(productService.getProductById(1), "Produk seharusnya berhasil ditambahkan.");
    }
}
