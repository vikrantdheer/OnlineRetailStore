package com.vikrant.mediaocean.service;

import com.vikrant.mediaocean.entity.Product;
import com.vikrant.mediaocean.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> getProductByID(Long id) {
        return productRepository.findById(id);
    }
}
