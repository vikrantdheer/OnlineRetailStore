package com.vikrant.mediaocean.service;

import com.vikrant.mediaocean.beans.BillBean;
import com.vikrant.mediaocean.entity.Bill;
import com.vikrant.mediaocean.entity.Product;
import com.vikrant.mediaocean.exception.BillNotFoundException;
import com.vikrant.mediaocean.exception.ProductAlreadyExistsException;
import com.vikrant.mediaocean.repository.BillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    final Logger logger = LoggerFactory.getLogger(getClass());

    public Optional<Bill> getBillById(Long billId) {
        verifyBillExistsWith(billId);
        return billRepository.findById(billId);
    }

    public Iterable<Bill> getAllBills() {
        Iterable<Bill> bill = billRepository.findAll();
        logger.info("returning all products");
        return bill;
    }

    public Bill createBill(BillBean billDetails) {
        logger.info("JSON received to create bill = " + billDetails);

        checkWhetherBillExists(billDetails.getBillId());

        Bill bill = Bill.withId(billDetails.getBillId())
                .havingProducts(billDetails.getProducts())
                .withQuantity(billDetails.getProductQuantity());

        Bill newBill = billRepository.save(bill);
        logger.info("Created product with id = " + newBill.getBillId());
        return newBill;
    }

    public void deleteBillById(Long billId) {
        verifyBillExistsWith(billId);
        billRepository.deleteById(billId);
    }

    public Bill updateBill(BillBean billUpdateInfo, Long billId) {
        Bill bill;

        verifyBillExistsWith(billId);

        Optional<Bill> billFound = billRepository.findById(billId);
        billFound.get();

        return new Bill();
    }

    private void verifyBillExistsWith(Long billId) {
        Optional<Bill> bill = billRepository.findById(billId);
        if (!bill.isPresent()) {
            throw new BillNotFoundException("Bill with id " + billId + " not found");
        }
        logger.info(" Bill exists with an id = " + billId);
    }

    private void checkWhetherBillExists(Long newBillId) {
        Optional<Bill> bill = billRepository.findById(newBillId);
        if (bill.isPresent()) {
            logger.info("Bill with id: " + newBillId + " already exists");
            throw new ProductAlreadyExistsException("Bill with id: " + newBillId + " already exists");
        } else {
            logger.info("Proceeding with insertion of new bill with id: " + newBillId);
        }
    }

}
