package com.matkap.ecommerce.dto.responseDto.product;

import java.math.BigDecimal;
import java.util.List;

public class ProductItemResponseDto {

    private Long id;

    private String product;

    private String SKU;

    private Long qtyInStock;

    private String productImage;

    private BigDecimal price;

    private List<String> variationOptionValues;




    public ProductItemResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public Long getQtyInStock() {
        return qtyInStock;
    }

    public void setQtyInStock(Long qtyInStock) {
        this.qtyInStock = qtyInStock;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<String> getVariationOptionValues() {
        return variationOptionValues;
    }

    public void setVariationOptionValues(List<String> variationOptionValues) {
        this.variationOptionValues = variationOptionValues;
    }
}

