package com.matkap.ecommerce.repository.product;

import com.matkap.ecommerce.model.product.Variation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariationRepository extends JpaRepository<Variation, Long> {
}
