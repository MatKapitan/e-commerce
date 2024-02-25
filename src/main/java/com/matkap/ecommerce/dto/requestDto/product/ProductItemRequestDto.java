package com.matkap.ecommerce.dto.requestDto.product;


import java.math.BigDecimal;

public class ProductItemRequestDto {

    private String SKU;

    private Long qty_in_stock;

    private String product_image;

    private BigDecimal price;

    private Long product_id;


    public ProductItemRequestDto() {
    }



    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public Long getQty_in_stock() {
        return qty_in_stock;
    }

    public void setQty_in_stock(Long qty_in_stock) {
        this.qty_in_stock = qty_in_stock;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

}
