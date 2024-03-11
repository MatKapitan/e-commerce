package com.matkap.ecommerce.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matkap.ecommerce.model.shopingCard.ShoppingCard;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_item")
public class ProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sku")
    private String sku;

    @Column(name = "qty_in_stock")
    private Long qtyInStock;
    @Column(name = "product_image")
    private String productImage;
    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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
