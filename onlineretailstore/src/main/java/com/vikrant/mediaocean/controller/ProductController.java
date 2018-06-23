package com.vikrant.mediaocean.controller;

import com.vikrant.mediaocean.beans.ProductBean;
import com.vikrant.mediaocean.entity.Product;
import com.vikrant.mediaocean.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@Api(value = "onlineretailstore", description = "Products")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(BillController.class);

    @Autowired
    private ProductService productService;

    @RequestMapping("/")
    public String home() {
        return "Online retail store application is up";
    }

    @ApiOperation(value = "View all products")
    @RequestMapping(value = "/products", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @ApiOperation(value = "View product by id")
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Optional<Product>> getProductByID(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Optional<Product>>(productService.getProductByID(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Add new product")
    @RequestMapping(value = "/products", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Product> addProduct(@ApiParam(value = "JSON for the new product", required = true) @Valid @RequestBody ProductBean productDetails) {

        Product product = productService.addProduct(productDetails);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getProductId())
                .toUri();
        logger.info("Setting header url with newly created product= " + product.getProductId());
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(product, responseHeaders, HttpStatus.CREATED);
    }



}
