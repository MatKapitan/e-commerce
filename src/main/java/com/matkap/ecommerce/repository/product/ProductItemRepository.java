package com.matkap.ecommerce.repository.product;

import com.matkap.ecommerce.model.product.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {

    @Query("""
            select (count(a) > 0)
            from ProductItem a
            where a.id = :id
            and a.qtyInStock >= :qty
            """)
    boolean isProductInStock(Long id, Long qty);

}
