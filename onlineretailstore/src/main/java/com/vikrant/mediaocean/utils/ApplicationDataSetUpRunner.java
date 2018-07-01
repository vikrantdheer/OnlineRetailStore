package com.vikrant.mediaocean.utils;

import com.vikrant.mediaocean.beans.BillBean;
import com.vikrant.mediaocean.beans.ProductBean;
import com.vikrant.mediaocean.beans.ProductDetailsForBilling;
import com.vikrant.mediaocean.entity.Bills;
import com.vikrant.mediaocean.service.BillService;
import com.vikrant.mediaocean.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.vikrant.mediaocean.utils.ProductCategory.A;
import static com.vikrant.mediaocean.utils.ProductCategory.B;
import static com.vikrant.mediaocean.utils.ProductCategory.C;

@Component
public class ApplicationDataSetUpRunner implements CommandLineRunner {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BillService billService;

    @Autowired
    private ProductService productService;

    @Override
    public void run(String... arg0) throws Exception {
        logger.info("Data set-up started");
        setUpProductData();
        setupBillData();
        logger.info("Data set-up finished");
    }

    private void setUpProductData() {
        productService.addProduct(new ProductBean(1, "Chips", A, 10.0));
        productService.addProduct(new ProductBean(2, "Biscuit", B, 20.0));
        productService.addProduct(new ProductBean(3, "Tea", C, 30.0));
        productService.addProduct(new ProductBean(4, "Jeans", A, 40.0));
        productService.addProduct(new ProductBean(5, "TV", B, 50.0));
        productService.addProduct(new ProductBean(6, "Lamp", C, 60.0));
        productService.addProduct(new ProductBean(7, "Table", A, 70.0));
        productService.addProduct(new ProductBean(8, "Apple", B, 80.0));
        productService.addProduct(new ProductBean(9, "Ketchup", C, 90.0));
        productService.addProduct(new ProductBean(10, "Bag", A, 100.0));
    }

    public void setupBillData() {

        logger.info("Create bill...START");
        Bills firstBill = billService.createBill(new Bills(0, 0, 0.0));
        logger.info("Create bill...DONE");

        Integer billId = firstBill.getBillId();
        BillBean billdetails = new BillBean();
        List<ProductDetailsForBilling> productsToBeAdded = new ArrayList<>();

        productsToBeAdded.add(new ProductDetailsForBilling(1, 1));
        productsToBeAdded.add(new ProductDetailsForBilling(2, 1));
        productsToBeAdded.add(new ProductDetailsForBilling(3, 1));
        productsToBeAdded.add(new ProductDetailsForBilling(4, 1));
        productsToBeAdded.add(new ProductDetailsForBilling(5, 1));

        billdetails.setProducts(productsToBeAdded);

        logger.info("Update bill...START");
        billService.updateBill(billdetails, billId);
        logger.info("Update bill...DONE");

        Optional<Bills> retrieveUpdatedbill = billService.getBillById(firstBill.getBillId());

        logger.info("Retrieved updated bill with id: " + retrieveUpdatedbill.get().getNoOfItems() + " with value: "
                + retrieveUpdatedbill.get().getTotalValue());

    }
}
