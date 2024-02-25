package com.matkap.ecommerce.repository.product;

import com.matkap.ecommerce.model.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
