package com.vikrant.mediaocean.controller;

import com.vikrant.mediaocean.beans.BillBean;
import com.vikrant.mediaocean.entity.Bills;
import com.vikrant.mediaocean.entity.Product;
import com.vikrant.mediaocean.service.BillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Api(description = "Bills")
public class BillController {

    @Autowired
    private BillService billService;

    private List<Product> productList = new ArrayList<>();

    private final Logger logger = LoggerFactory.getLogger(BillController.class);

    @ApiOperation(value = "Fetches a particular bill details")
    @RequestMapping(value = "/bills/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Optional<Bills>> getBillById(@PathVariable Integer id) {
        return new ResponseEntity<Optional<Bills>>(billService.getBillById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Fetches all bills from the database")
    @RequestMapping(value = "/bills", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Iterable<Bills>> getAllBills() {
        return new ResponseEntity<>(billService.getAllBills(), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes Bill")
    @RequestMapping(value = "/bills/{id}", produces = "application/json", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteBill(@PathVariable Integer billId) {
        billService.deleteBillById(billId);
        return new ResponseEntity<>("{\"status\": \"success\"}", HttpStatus.OK);
    }

    @ApiOperation(value = "Creates an Bill and returns an id.")
    @RequestMapping(value = "/bills", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<Bills> createBill() {
        logger.info("Request recieved to create Bill = ");
        Bills bill = billService.createBill(new Bills(0, 0, 0.0));

        logger.info("Created Bill with id = " + bill.getBillId());
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bill.getBillId())
                .toUri();
        logger.info("Setting header url with newly created Bill= " + bill.getBillId());
        responseHeaders.setLocation(newPollUri);

        return new ResponseEntity<>(bill, responseHeaders, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Add or Remove products from the Bill")
    @RequestMapping(value = "/bills/{id}", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity<Optional<Bills>> updateBill(@RequestBody BillBean billDetails, @PathVariable Integer id) {
        logger.info("Request received to update the bill with id: %s  ", id);
        Optional<Bills> updatedBill = billService.updateBill(billDetails, id);
        return new ResponseEntity<Optional<Bills>>(updatedBill, HttpStatus.OK);
    }
}
