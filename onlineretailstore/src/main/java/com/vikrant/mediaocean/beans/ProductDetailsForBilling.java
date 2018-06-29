package com.vikrant.mediaocean.beans;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ProductDetailsForBilling {

    @NotNull
    private Integer productId;

    private Integer quantity;

    public ProductDetailsForBilling() {
        super();
    }

    public ProductDetailsForBilling(@NotNull Integer productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDetailsForBilling that = (ProductDetailsForBilling) o;
        return quantity == that.quantity &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productId, quantity);
    }

    @Override
    public String toString() {
        return "ProductDetailsForBilling{" +
                "productId='" + productId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
