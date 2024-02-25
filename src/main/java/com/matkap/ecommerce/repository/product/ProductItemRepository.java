package com.matkap.ecommerce.repository.product;

import com.matkap.ecommerce.model.product.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {
}
