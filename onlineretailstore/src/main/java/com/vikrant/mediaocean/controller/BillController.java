package com.vikrant.mediaocean.controller;

import com.vikrant.mediaocean.beans.BillBean;
import com.vikrant.mediaocean.entity.Bill;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@Api(description = "Bills")
public class BillController {

    @Autowired
    private BillService billService;

    private List<Product> productList;

    private final Logger logger = LoggerFactory.getLogger(BillController.class);

    @ApiOperation(value = "Fetches a particular bill details")
    @RequestMapping(value = "/bills/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Optional<Bill>> getBillById(@PathVariable Long id) {
        return new ResponseEntity<Optional<Bill>>(billService.getBillById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Fetches all bills from the database")
    @RequestMapping(value = "/bills", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Iterable<Bill>> getAllBills() {
        return new ResponseEntity<>(billService.getAllBills(), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes Bill")
    @RequestMapping(value = "/bills/{id}", produces = "application/json", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteBill(@PathVariable Long billId) {
        billService.deleteBillById(billId);
        return new ResponseEntity<>("{\"status\": \"success\"}", HttpStatus.OK);
    }

    @ApiOperation(value = "Creates an Bill and returns an id.")
    @RequestMapping(value = "/bills", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<Bill> createBill() {
        logger.info("Request recieved to create Bill = ");
        Bill bill = billService.createBill(new BillBean(1L , productList, 2));

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
    public ResponseEntity<Bill> updateBill(@RequestBody BillBean billDetails, @PathVariable Long id) {
        Bill updatedBill = billService.updateBill(billDetails, id);
        logger.info("Request recieved =  " + billDetails);
        return new ResponseEntity<>(updatedBill, HttpStatus.OK);
    }
}
