package com.vikrant.mediaocean.serviceTests;


import com.vikrant.mediaocean.beans.BillBean;
import com.vikrant.mediaocean.beans.ProductDetailsForBilling;
import com.vikrant.mediaocean.entity.Bills;
import com.vikrant.mediaocean.exception.BillNotFoundException;
import com.vikrant.mediaocean.service.BillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BillServiceTest {

    @Autowired
    private BillService billService;

    @Test
    public void shouldCreateBillSuccesfully() {
        Bills firstBill = billService.createBill(new Bills(0, 0, 0.0));
        Bills secondBill = billService.getBillById(firstBill.getBillId()).get();
        assertThat(firstBill.getBillId()).isEqualTo(secondBill.getBillId());
    }

    @Test(expected = BillNotFoundException.class)
    public void shouldNotUpdateBillWithInvalidBillId() {

        Bills bill = billService.createBill(new Bills(0, 0, 0.0));

        BillBean billBean = new BillBean();

        List<ProductDetailsForBilling> productsToBeAdded = new ArrayList<ProductDetailsForBilling>();

        productsToBeAdded.add(new ProductDetailsForBilling(1, 1));
        productsToBeAdded.add(new ProductDetailsForBilling(2, 2));
        productsToBeAdded.add(new ProductDetailsForBilling(3, 3));

        billBean.setProducts(productsToBeAdded);

        billService.updateBill(billBean, 0);
        Optional<Bills> retrieveUpdatedBill = billService.getBillById(bill.getBillId());
    }

    @Test
    public void shouldSaveTheMultipleValidProductsSuccessfully() {
        Bills bill = billService.createBill(new Bills(10, 0, 0.0));
        Integer billId = bill.getBillId();

        BillBean billBean = new BillBean();

        List<ProductDetailsForBilling> productsToBeAdded = new ArrayList<ProductDetailsForBilling>();

        productsToBeAdded.add(new ProductDetailsForBilling(1, 1));
        productsToBeAdded.add(new ProductDetailsForBilling(2, 1));
        productsToBeAdded.add(new ProductDetailsForBilling(3, 1));

        billBean.setProducts(productsToBeAdded);

        billService.updateBill(billBean, billId);

        Optional<Bills> retrieveUpdatedBill = billService.getBillById(bill.getBillId());

        assertThat(retrieveUpdatedBill.get().getNoOfItems()).isEqualTo(3);
        assertThat(retrieveUpdatedBill.get().getTotalValue()).isEqualTo(65);
    }

    @Test
    public void shouldUpdateCategory_A_products() {
        Bills bill = billService.createBill(new Bills(0, 0, 0.0));
        Integer billId = bill.getBillId();

        BillBean billBean = new BillBean();

        List<ProductDetailsForBilling> productsToBeAdded = new ArrayList<ProductDetailsForBilling>();

        productsToBeAdded.add(new ProductDetailsForBilling(4, 2));

        billBean.setProducts(productsToBeAdded);

        billService.updateBill(billBean, billId);
        Optional<Bills> retrieveUpdatedBill = billService.getBillById(bill.getBillId());

        assertThat(retrieveUpdatedBill.get().getNoOfItems()).isEqualTo(1);
        assertThat(retrieveUpdatedBill.get().getTotalValue()).isEqualTo(88);
    }

    @Test
    public void shouldUpdateCategory_B_products() {
        Bills bill = billService.createBill(new Bills(0, 0, 0.0));
        Integer billId = bill.getBillId();

        BillBean billBean = new BillBean();

        List<ProductDetailsForBilling> productsToBeAdded = new ArrayList<ProductDetailsForBilling>();

        productsToBeAdded.add(new ProductDetailsForBilling(5, 2));

        billBean.setProducts(productsToBeAdded);

        billService.updateBill(billBean, billId);
        Optional<Bills> retrieveUpdatedBill = billService.getBillById(bill.getBillId());

        assertThat(retrieveUpdatedBill.get().getNoOfItems()).isEqualTo(1);
        assertThat(retrieveUpdatedBill.get().getTotalValue()).isEqualTo(120);
    }

    @Test
    public void shouldUpdateCategory_C_products() {
        Bills bill = billService.createBill(new Bills(0, 0, 0.0));
        Integer billId = bill.getBillId();

        BillBean billBean = new BillBean();

        List<ProductDetailsForBilling> productsToBeAdded = new ArrayList<ProductDetailsForBilling>();

        productsToBeAdded.add(new ProductDetailsForBilling(6, 2));

        billBean.setProducts(productsToBeAdded);

        billService.updateBill(billBean, billId);
        Optional<Bills> retrieveUpdatedBill = billService.getBillById(bill.getBillId());

        assertThat(retrieveUpdatedBill.get().getNoOfItems()).isEqualTo(1);
        assertThat(retrieveUpdatedBill.get().getTotalValue()).isEqualTo(120);
    }

}
