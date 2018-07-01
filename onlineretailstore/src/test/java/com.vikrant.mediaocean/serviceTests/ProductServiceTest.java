package com.vikrant.mediaocean.serviceTests;

import com.vikrant.mediaocean.beans.ProductBean;
import com.vikrant.mediaocean.entity.Product;
import com.vikrant.mediaocean.exception.InvalidProductException;
import com.vikrant.mediaocean.exception.ProductNotFoundException;
import com.vikrant.mediaocean.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.vikrant.mediaocean.utils.ProductCategory.A;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    ProductBean validProduct;

    @Test
    public void productShouldBeSuccessfullyCreated() {
        validProduct = new ProductBean(11, "ProductTestName 1", A, 100.0);
        Product expected = productService.addProduct(validProduct);
        Product actual = productService.getProductByID(expected.getProductId());
        assertThat(expected).isEqualTo(actual);
    }

    @Test(expected = InvalidProductException.class)
    public void productShouldNotBeCreatedWithInvalidDetails() {
        productService.addProduct(new ProductBean(11, "", A, 0.0));
    }

    @Test
    public void productShouldBeSuccessfullyUpdated() {
        validProduct = new ProductBean(11, "ProductTestName 1", A, 100.0);
        Product expected = productService.addProduct(validProduct);
        Product updatedExpectedProduct = productService.updateProductWith(validProduct, validProduct.getProductId());
        Product actual = productService.getProductByID(expected.getProductId());
        assertThat(updatedExpectedProduct).isEqualTo(actual);
        productService.deleteProductById(validProduct.getProductId());
    }

    @Test(expected = ProductNotFoundException.class)
    public void productShouldNotBeUpdatedWithInvalidDetails() {
        validProduct = new ProductBean(11, "ProductTestName 1", A, 100.0);
        productService.addProduct(validProduct);
        productService.updateProductWith(validProduct, 0);
        productService.deleteProductById(validProduct.getProductId());
    }

    @Test(expected = ProductNotFoundException.class)
    public void productShouldBeSuccessfullyDeleted() {
        validProduct = new ProductBean(11, "ProductTestName 1", A, 100.0);
        productService.addProduct(validProduct);
        productService.deleteProductById(validProduct.getProductId());
        Product product = productService.getProductByID(validProduct.getProductId());
    }
}
