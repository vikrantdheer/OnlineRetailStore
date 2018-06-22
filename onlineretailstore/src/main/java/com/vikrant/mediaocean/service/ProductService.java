package com.vikrant.mediaocean.service;

import com.vikrant.mediaocean.beans.ProductBean;
import com.vikrant.mediaocean.entity.Product;
import com.vikrant.mediaocean.exception.ProductAlreadyExistsException;
import com.vikrant.mediaocean.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        checkWhetherProductExists(productDetails.getProductId());

        Product product = Product.withId(productDetails.getProductId())
                .havingName(productDetails.getProductName())
                .ofCategory(productDetails.getProductCategory())
                .costing(productDetails.getRate());

        product = productRepository.save(product);
        logger.info("Created product with id = " + product.getProductId());
        return product;
    }

    private void checkWhetherProductExists(Long newProductId) {
        Optional<Product> product = productRepository.findById(newProductId);
        if (product.isPresent()) {
            logger.info("Product with id: " + newProductId + " already exists");
            throw new ProductAlreadyExistsException("Product with id: " + newProductId + " already exists");
        }else{
            logger.info("Proceeding with insertion of new product with id: " + newProductId);
        }
    }
}
