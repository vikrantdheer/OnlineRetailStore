package com.vikrant.mediaocean.entity;

import com.vikrant.mediaocean.utils.ProductCategory;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @NotNull
    private String productName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @NotNull
    @DecimalMin(value = "0.1")
    private double rate;

    public Product() {
        super();
    }

    public Product(@NotNull Integer productId, @NotNull String productName, @NotNull ProductCategory productCategory, @NotNull double rate) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.rate = rate;
    }

    public static ProductBuilder withId(Integer productId) {
        return new ProductBuilder(productId);
    }

    public static class ProductBuilder {
        private Integer productId;
        private String productName;
        private ProductCategory productCategory;
        private double rate;

        private ProductBuilder(Integer productId) {
            this.productId = productId;
        }

        public ProductBuilder havingName(String productName) {
            this.productName = productName;
            return this;
        }

        public ProductBuilder ofCategory(ProductCategory productCategory) {
            this.productCategory = productCategory;
            return this;
        }

        public Product costing(double rate) {
            return new Product(productId, productName, productCategory, rate);
        }
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
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
        Product product = (Product) o;
        return Double.compare(product.rate, rate) == 0 &&
                Objects.equals(productId, product.productId) &&
                Objects.equals(productName, product.productName) &&
                productCategory == product.productCategory;
    }

    @Override
    public int hashCode() {

        return Objects.hash(productId, productName, productCategory, rate);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productCategory=" + productCategory +
                ", rate=" + rate +
                '}';
    }
}
