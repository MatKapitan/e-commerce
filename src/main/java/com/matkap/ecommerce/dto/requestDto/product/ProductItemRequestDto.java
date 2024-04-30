package com.matkap.ecommerce.dto.requestDto.product;


import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class ProductItemRequestDto {

    @NotBlank(message = "sku cannot be blank")
    private String sku;
    @NotNull(message = "quantityInStock cannot be null")
    @Positive(message = "quantityInStock must be positive")
    private Long quantityInStock;
    @NotBlank(message = "productImage cannot be blank")
    private String productImage;
    @NotNull(message = "price cannot be blank")
    @Positive(message = "price must be positive")
    @Digits(integer = 10, fraction = 2, message = "price can have only 2 decimal places")
    private BigDecimal price;
    @NotNull(message = "productId cannot be null")
    private Long productId;

    public ProductItemRequestDto(String sku, Long quantityInStock, String productImage, BigDecimal price, Long productId) {
        this.sku = sku;
        this.quantityInStock = quantityInStock;
        this.productImage = productImage;
        this.price = price;
        this.productId = productId;
    }

    public ProductItemRequestDto() {
    }



    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Long getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Long quantityInStock) {
        this.quantityInStock = quantityInStock;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

}
