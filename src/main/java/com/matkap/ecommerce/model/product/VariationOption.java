package com.matkap.ecommerce.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "variation_option")
public class VariationOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "name")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "variation_id", referencedColumnName = "id")
    private Variation variation;

    @Column(name = "value") //TODO change to something better (sql insert too) cant be value huhh..
    private String value;

    @JsonIgnore
    @ManyToMany(mappedBy = "variationOptions")
    private List<ProductItem> productItems = new ArrayList<>();


    public VariationOption() {
    }

    public VariationOption(Variation variation, String value) {
        this.variation = variation;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Variation getVariation() {
        return variation;
    }

    public void setVariation(Variation variation) {
        this.variation = variation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(List<ProductItem> productItems) {
        this.productItems = productItems;
    }
}
