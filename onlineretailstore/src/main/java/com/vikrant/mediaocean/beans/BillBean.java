package com.vikrant.mediaocean.beans;

import com.google.common.base.Objects;
import com.vikrant.mediaocean.entity.Product;

import javax.validation.constraints.NotNull;
import java.util.List;

public class BillBean {

    @NotNull
    private Long billId;

    @NotNull
    private List<Product> products;

    @NotNull
    private Integer productQuantity;

    public BillBean() {
        super();
    }

    public BillBean(@NotNull Long billId, @NotNull List<Product> products, @NotNull Integer productQuantity) {
        this.billId = billId;
        this.products = products;
        this.productQuantity = productQuantity;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillBean billBean = (BillBean) o;
        return Objects.equal(billId, billBean.billId) &&
                Objects.equal(products, billBean.products) &&
                Objects.equal(productQuantity, billBean.productQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(billId, products, productQuantity);
    }

    @Override
    public String toString() {
        return "BillBean{" +
                "billId='" + billId + '\'' +
                ", products=" + products +
                ", productQuantity=" + productQuantity +
                '}';
    }
}
