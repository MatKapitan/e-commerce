package com.matkap.ecommerce.model.product;

import jakarta.persistence.*;

@Entity
@Table(name = "Variation")
public class Variation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private ProductCategory productCategory;

    private String name;

//    @OneToMany(mappedBy = "variation")
//    private List<VariationOption> variationOptions = new ArrayList<>();

    //TODO Bidirectional bad


    public Variation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<VariationOption> getVariationOptions() {
//        return variationOptions;
//    }
//
//    public void setVariationOptions(List<VariationOption> variationOptions) {
//        this.variationOptions = variationOptions;
//    }
}
