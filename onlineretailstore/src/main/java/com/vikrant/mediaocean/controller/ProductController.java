package com.vikrant.mediaocean.controller;

import com.vikrant.mediaocean.entity.Product;
import com.vikrant.mediaocean.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Api(value = "onlineretailstore", description = "Products")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(BillController.class);

    @Autowired
    private ProductService productService;

    @RequestMapping("/")
    public String home() {
        return "Hello products";
    }

    @ApiOperation(value = "View product by id", response = Product.class, produces = "application/json")
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Product>> getProductByID(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Optional<Product>>(productService.getProductByID(id), HttpStatus.OK);
    }

}
