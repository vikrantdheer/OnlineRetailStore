package com.vikrant.mediaocean.entity;

import com.google.common.base.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "BILL")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String billId;

    @NotNull
    private int noOfItems;

    @NotNull
    private double totalCost;

    @NotNull
    private double totalTax;

    @NotNull
    private double totalValue;

    public Bill() {
        super();
    }

    public Bill(String billId, @NotNull int noOfItems, @NotNull double totalCost, @NotNull double totalTax, @NotNull double totalValue) {
        this.billId = billId;
        this.noOfItems = noOfItems;
        this.totalCost = totalCost;
        this.totalTax = totalTax;
        this.totalValue = totalValue;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
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
