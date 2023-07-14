package com.example.sample.DataAccess.Repositories.Products;

import Entities.Products.ProductStatistics;
import com.example.sample.Common.Exceptions.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public class ProductsDataAccess implements com.example.sample.DataAccess.Abstract.Repositories.Products.ProductsDataAccess {
    private final ProductsRepository productsRepository;
    private final RecordNotFoundException recordNotFoundException = new RecordNotFoundException();

    public ProductsDataAccess(ProductsRepository productsRepository) {

        this.productsRepository = productsRepository;
    }

    @Override
    @CacheEvict(value = "book.statistics", key = "#name")
    public void SubmitProduct(String name, Long price, int purchaseCount) {
        try {
            productsRepository.InsertProduct(name, price, purchaseCount);
        } catch (Exception e) {
            // @TODO Logging errors are discarded
            System.out.println(e);
        }
    }

    @Override
    @Cacheable("book.statistics")
    public ProductStatistics GetProductStatistics(String productName) throws RecordNotFoundException {
        var statistics = productsRepository.GetProductStatistics(productName);
        if (statistics.AveragePrice == null || statistics.Count == null) {
            throw recordNotFoundException;
        }
        return new Entities.Products.ProductStatistics(productName, statistics.Count, statistics.AveragePrice);
    }
}
