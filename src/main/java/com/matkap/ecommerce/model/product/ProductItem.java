package com.matkap.ecommerce.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matkap.ecommerce.model.shopingCard.ShoppingCard;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Product_item")
public class ProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String SKU;

    private Long qty_in_stock;

    private String product_image;

    private BigDecimal price;

    @ManyToOne()
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_configuration",
                    joinColumns = @JoinColumn(name = "product_item_id"),
                    inverseJoinColumns = @JoinColumn(name = "variation_option_id"))
    private List<VariationOption> variationOptions = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "productItem")
    private List<ShoppingCard> shoppingCards;

    public ProductItem() {
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


    public void addVariationOption(VariationOption variationOption){
        variationOptions.add(variationOption);
    }

    public void deleteVariationOption(VariationOption variationOption){
        variationOptions.remove(variationOption);
    }
}
