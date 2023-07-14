package com.example.sample.DataAccess.Repositories.Products;

import com.example.sample.DataAccess.Models.Products.Product;
import com.example.sample.DataAccess.Models.Products.ProductStatistics;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface ProductsRepository extends CrudRepository<Product, Long> {

    @Modifying
    @Query("""
                INSERT INTO SHOP."Products" ("Name", "Price", "PurchaseCount") VALUES (:name,:price,:purchase_count)
            """)
    void InsertProduct(@Param("name") String name, @Param("price") Long price, @Param("purchase_count") int purchaseCount);

    @Query("""
            SELECT SUM("PurchaseCount") as "Count",AVG("Price") as "AveragePrice"
            FROM SHOP."Products" WHERE "Name" = :name
            """)
    ProductStatistics GetProductStatistics(@Param("name") String name);
}
