package com.vikrant.mediaocean.service;

import com.vikrant.mediaocean.beans.BillBean;
import com.vikrant.mediaocean.beans.ProductDetailsForBilling;
import com.vikrant.mediaocean.entity.Bills;
import com.vikrant.mediaocean.entity.Order;
import com.vikrant.mediaocean.entity.Product;
import com.vikrant.mediaocean.exception.BillAlreadyExistsException;
import com.vikrant.mediaocean.exception.BillNotFoundException;
import com.vikrant.mediaocean.repository.BillRepository;
import com.vikrant.mediaocean.repository.OrderRepository;
import com.vikrant.mediaocean.utils.ProductCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static com.vikrant.mediaocean.utils.ProductCategory.A;
import static com.vikrant.mediaocean.utils.ProductCategory.B;
import static com.vikrant.mediaocean.utils.ProductCategory.C;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    final Logger logger = LoggerFactory.getLogger(getClass());

    public Optional<Bills> getBillById(Integer billId) {
        verifyBillExistsWith(billId);
        return billRepository.findById(billId);
    }

    public Iterable<Bills> getAllBills() {
        Iterable<Bills> bill = billRepository.findAll();
        logger.info("returning all products");
        return bill;
    }

    @Transactional
    public Bills createBill(Bills billDetails) {
        logger.info("JSON received to create bill = " + billDetails);

        checkWhetherBillExists(billDetails.getBillId());

        Bills bill = Bills.withId(billDetails.getBillId())
                .withNumberOfItems(billDetails.getNoOfItems())
                .havingTotalValueOf(0.0);

        Bills newBill = billRepository.save(bill);
        logger.info("Created bill with id = " + newBill.getBillId());
        return newBill;
    }

    @Transactional
    public void deleteBillById(Integer billId) {
        verifyBillExistsWith(billId);
        billRepository.deleteById(billId);
    }

    @Transactional
    public Optional<Bills> updateBill(@NotNull BillBean billUpdateInfo, Integer billId) {

        verifyBillExistsWith(billId);

        if (null != billUpdateInfo.getProducts()) {
            List<ProductDetailsForBilling> productList = billUpdateInfo.getProducts();
            Iterator<ProductDetailsForBilling> productsToBeAdded = productList.iterator();
            while (productsToBeAdded.hasNext()) {
                ProductDetailsForBilling productInfo = productsToBeAdded.next();

                logger.info("Products to be added : " + productInfo);
                Bills bill = addProductsToBill(billId, productInfo.getProductId(), productInfo.getQuantity());
                computeTotalValues(bill);
            }
        }

        return billRepository.findById(billId);
    }

    private Bills addProductsToBill(Integer billId, Integer productId, int quantity) {
        Optional<Bills> bill = billRepository.findById(billId);
        Product productFound = productService.verifyProductExistsBy(productId);

        Order order = new Order(productFound, quantity);
        orderRepository.save(order);

        List<Order> currentOrder = bill.get().getOrders();

        if (0 < currentOrder.size()) {
            Order existingOrder = getOrderWithProductId(productId, currentOrder);
            if (existingOrder == null) {
                bill.get().getOrders().add(order);
            } else {
                Integer newQty = existingOrder.getQuantity() + quantity;
                existingOrder.setQuantity(newQty);
            }
        } else {
            currentOrder = new ArrayList<>();
            currentOrder.add(order);
            bill.get().setOrders(currentOrder);
        }

        return billRepository.save(bill.get());
    }

    private void computeTotalValues(Bills bill) {

        int noOfItems = 0;
        double totalValue = 0;
        double totalCost = 0;

        if (null != bill.getOrders()) {
            List<Order> orders = bill.getOrders();
            Iterator<Order> orderIterator = orders.iterator();
            while (orderIterator.hasNext()) {
                Order order = orderIterator.next();
                double saleValue = computeValueForItem(order.getQuantity(), order.getProduct().getProductCategory(),
                        order.getProduct().getRate());
                logger.info(String.format("Sale value:  %s and Order: %s ", saleValue, order));
                totalValue += saleValue;
                totalCost += order.getQuantity() * order.getProduct().getRate();
                noOfItems++;
            }
        }
        bill.setNoOfItems(noOfItems);
        bill.setTotalValue(totalValue);
        bill.setTotalCost(totalCost);
        bill.setTotalTax(totalValue - totalCost);
        billRepository.save(bill);
    }

    private double computeValueForItem(long quantity, ProductCategory productCategory, double rate) {
        logger.info("productCategory : " + productCategory + "  quantity = " + quantity + "  rate = " + rate);
        double saleValue = 0;
        if (productCategory.equals(A)) {
            saleValue = quantity * rate * 1.1;

        } else if (productCategory.equals(B)) {
            saleValue = quantity * rate * 1.2;

        } else if (productCategory.equals(C)) {
            saleValue = quantity * rate;
        }
        return saleValue;
    }

    private Order getOrderWithProductId(Integer productId, List<Order> currentOrder) {
        for (int i = 0; i < currentOrder.size(); i++) {
            Order order = currentOrder.get(i);
            if (productId.equals(order.getProduct().getProductId())) {
                logger.info("Order has product: " + productId);
                return order;
            }
        }
        logger.info("Current list of order do not have product: " + productId);
        return null;
    }

    private void verifyBillExistsWith(Integer billId) {
        Optional<Bills> bill = billRepository.findById(billId);
        if (!bill.isPresent()) {
            throw new BillNotFoundException("Bill with id " + billId + " not found");
        }
        logger.info("Bill exists with an id = " + billId);
    }

    private void checkWhetherBillExists(Integer newBillId) {
        Optional<Bills> bill = billRepository.findById(newBillId);
        if (bill.isPresent()) {
            logger.info("Bill with id: " + newBillId + " already exists");
            throw new BillAlreadyExistsException("Bill with id: " + newBillId + " already exists");
        }
    }

}
