package com.matkap.ecommerce.model.product;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_category")
public class ProductCategory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id", referencedColumnName = "id")
    @JsonBackReference
    private ProductCategory parent_category;

    @JsonIgnore
    @OneToMany(mappedBy = "parent_category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private  List<ProductCategory> children_category = new ArrayList<>();
    @Column(name = "category_name")
    private String categoryName;

    @ManyToMany(mappedBy = "productCategory")
    private List<Promotion> promotions = new ArrayList<>();







    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductCategory getParent_category() {
        return parent_category;
    }

    public void setParent_category(ProductCategory parent_category) {
        this.parent_category = parent_category;
    }

    public List<ProductCategory> getChildren_category() {
        return children_category;
    }

    public void setChildren_category(List<ProductCategory> children_category) {
        this.children_category = children_category;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }
}
