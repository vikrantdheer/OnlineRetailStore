package com.vikrant.mediaocean.beans;

import com.google.common.base.Objects;
import com.vikrant.mediaocean.utils.ProductCategory;

import javax.validation.constraints.NotNull;

public class ProductBean {

    @NotNull
    private Long productId;

    @NotNull
    private String productName;

    @NotNull
    private ProductCategory productCategory;

    @NotNull
    private double rate;

    public ProductBean() {
    }

    public ProductBean(@NotNull Long productId, @NotNull String productName, @NotNull ProductCategory productCategory, @NotNull double rate) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.rate = rate;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductBean that = (ProductBean) o;
        return Double.compare(that.rate, rate) == 0 &&
                Objects.equal(productId, that.productId) &&
                Objects.equal(productName, that.productName) &&
                productCategory == that.productCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productId, productName, productCategory, rate);
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productCategory=" + productCategory +
                ", rate=" + rate +
                '}';
    }
}
