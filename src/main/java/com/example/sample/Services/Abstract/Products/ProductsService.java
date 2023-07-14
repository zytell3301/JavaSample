package com.example.sample.Services.Abstract.Products;

import com.example.sample.Services.Impl.Common.Models.SimpleResponse;
import com.example.sample.Services.Impl.Products.Models.GetProductStatisticsResponse;
import com.example.sample.Services.Impl.Products.Models.SubmitProductRequest;

public interface ProductsService {
    public SimpleResponse SubmitProduct(SubmitProductRequest request);
    public GetProductStatisticsResponse GetProductStatistics(String productName) throws Exception;
}
