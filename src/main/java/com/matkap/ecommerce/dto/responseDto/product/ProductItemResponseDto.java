package com.matkap.ecommerce.dto.responseDto.product;

import com.matkap.ecommerce.model.product.Product;
import com.matkap.ecommerce.model.product.VariationOption;

import java.math.BigDecimal;
import java.util.List;

public class ProductItemResponseDto {

    private Long id;

    private Product product;

    private String SKU;

    private Long qty_in_stock;

    private String product_image;

    private BigDecimal price;

    private List<VariationOption> variationOptions;




    public ProductItemResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public List<VariationOption> getVariationOptions() {
        return variationOptions;
    }

    public void setVariationOptions(List<VariationOption> variationOptions) {
        this.variationOptions = variationOptions;
    }
}

