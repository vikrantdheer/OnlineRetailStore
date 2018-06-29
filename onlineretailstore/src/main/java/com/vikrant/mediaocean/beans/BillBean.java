package com.vikrant.mediaocean.beans;

import com.google.common.base.Objects;
import com.sun.istack.internal.NotNull;

import java.util.List;

public class BillBean {

    @NotNull
    private List<ProductDetailsForBilling> productsToBeAddedForBilling;

    public BillBean() {
        super();
    }

    public BillBean(@NotNull List<ProductDetailsForBilling> products) {
        this.productsToBeAddedForBilling = products;
    }

    public List<ProductDetailsForBilling> getProducts() {
        return productsToBeAddedForBilling;
    }

    public void setProducts(List<ProductDetailsForBilling> products) {
        this.productsToBeAddedForBilling = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillBean billBean = (BillBean) o;
        return Objects.equal(productsToBeAddedForBilling, billBean.productsToBeAddedForBilling);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productsToBeAddedForBilling);
    }

    @Override
    public String toString() {
        return "Bill{" +
                ", products to be added:" + productsToBeAddedForBilling +
                '}';
    }
}

