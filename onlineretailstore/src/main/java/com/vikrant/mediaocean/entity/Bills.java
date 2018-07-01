package com.vikrant.mediaocean.entity;

import com.google.common.base.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "BILLS")
public class Bills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer billId;

    private int noOfItems;

    private double totalCost;

    private double totalTax;

    private double totalValue;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Order> orders;

    public Bills() {
        super();
    }

    public Bills(Integer billId, int noOfItems, double totalValue) {
        this.billId = billId;
        this.noOfItems = noOfItems;
        this.totalValue = totalValue;
    }

    public static BillBuilder withId(Integer billId) {
        return new BillBuilder(billId);
    }

    public static class BillBuilder {
        private Integer billId;
        private Integer numberOfItems;

        public BillBuilder(Integer billId) {
            this.billId = billId;
        }

        public BillBuilder withNumberOfItems(Integer numberOfItems) {
            this.numberOfItems = numberOfItems;
            return this;
        }

        public Bills havingTotalValueOf(double totalValue) {
            return new Bills(this.billId, numberOfItems, totalValue);
        }
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public int getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bills bill = (Bills) o;
        return noOfItems == bill.noOfItems &&
                Double.compare(bill.totalCost, totalCost) == 0 &&
                Double.compare(bill.totalTax, totalTax) == 0 &&
                Double.compare(bill.totalValue, totalValue) == 0 &&
                Objects.equal(billId, bill.billId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(billId, noOfItems, totalCost, totalTax, totalValue);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId='" + billId + '\'' +
                ", noOfItems=" + noOfItems +
                ", totalCost=" + totalCost +
                ", totalTax=" + totalTax +
                ", totalValue=" + totalValue +
                '}';
    }


}
