package com.vikrant.mediaocean.service;

import com.vikrant.mediaocean.beans.ProductBean;
import com.vikrant.mediaocean.entity.Product;
import com.vikrant.mediaocean.exception.*;
import com.vikrant.mediaocean.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.vikrant.mediaocean.utils.ProductCategory.*;


@Service
public class ProductService {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> getProductByID(Long id) {
        return productRepository.findById(id);
    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(ProductBean productDetails) {
        logger.info("JSON received to create product = " + productDetails);

        validateJson(productDetails);
        checkWhetherProductExists(productDetails.getProductId());

        Product product = Product.withId(productDetails.getProductId())
                .havingName(productDetails.getProductName())
                .ofCategory(productDetails.getProductCategory())
                .costing(productDetails.getRate());

        product = productRepository.save(product);
        logger.info("Created product with id = " + product.getProductId());
        return product;
    }

    public void deleteProductById(Long productId) {
        verifyProductExistsBy(productId);
        productRepository.deleteById(productId);
    }

    public Product updateProductWith(ProductBean newDetails, Long productId) {
        Product product;

        verifyProductExistsBy(productId);

        Optional<Product> productFound = productRepository.findById(newDetails.getProductId());
        product = productFound.get().withId(newDetails.getProductId())
                .havingName(newDetails.getProductName())
                .ofCategory(newDetails.getProductCategory())
                .costing(newDetails.getRate());

        logger.info("Updated product id = " + newDetails.getProductId());

        return productRepository.save(product);

    }

    private void validateJson(ProductBean productDetails) {
        if (0.0 >= productDetails.getRate()) {
            throw new InvalidProductPriceException("Product price cannot be less than 0.1");
        } else if (null == productDetails.getProductId()) {
            throw new InvalidProductIDException("Product ID cannot be blank");
        } else if (productDetails.getProductCategory().toString().equals(A) || productDetails.getProductCategory().toString().equals(B) || productDetails.getProductCategory().toString().equals(C)) {
            throw new InvalidProductCategoryException("Product Category should be A, B or C");
        } else if (productDetails.getProductName().isEmpty() && productDetails.getProductName() == null) {
            throw new InvalidProductNameException("Product should have some name");
        }
    }

    private void checkWhetherProductExists(Long newProductId) {
        Optional<Product> product = productRepository.findById(newProductId);
        if (product.isPresent()) {
            logger.info("Product with id: " + newProductId + " already exists");
            throw new ProductAlreadyExistsException("Product with id: " + newProductId + " already exists");
        } else {
            logger.info("Proceeding with insertion of new product with id: " + newProductId);
        }
    }

    private void verifyProductExistsBy(Long id) {
        logger.info("Verifying if the product exists with an id = " + id);
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
    }


}
