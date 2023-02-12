package com.winter.market.core.repository;

import com.winter.market.core.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
  /*  @Query("select p from Product p where (p.price >= :priceMinFilter or :priceMinFilter is null) " +
            "and(p.price <= :priceMaxFilter or :priceMaxFilter is null) " +
            "and (p.title like :titleFilter%)")
    List<Product> findWithFilter(@Param("priceMinFilter") BigDecimal priceMinFilter,
                                 @Param("priceMaxFilter") BigDecimal priceMaxFilter,
                                 @Param("titleFilter") String titleFilter);*/

}
