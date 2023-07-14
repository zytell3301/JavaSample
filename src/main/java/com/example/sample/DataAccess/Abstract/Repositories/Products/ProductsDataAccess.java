package com.example.sample.DataAccess.Abstract.Repositories.Products;

import Entities.Products.ProductStatistics;
import com.example.sample.Common.Exceptions.RecordNotFoundException;

public interface ProductsDataAccess {
    public void SubmitProduct(String name, Long price, int purchaseCount);
    public ProductStatistics GetProductStatistics(String productName) throws RecordNotFoundException;
}
