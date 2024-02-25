package com.matkap.ecommerce.model.product;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Product_category")
public class ProductCategory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id", referencedColumnName = "id")
    @JsonBackReference
    private ProductCategory parent_category;

    @OneToMany(mappedBy = "parent_category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @JsonManagedReference
    private  List<ProductCategory> children_category = new ArrayList<>();

// TODO Data Integrity Violation: we cannot process your request.
    private String category_name;

    @ManyToMany(mappedBy = "productCategory")
    private List<Promotion> promotions;







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

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
