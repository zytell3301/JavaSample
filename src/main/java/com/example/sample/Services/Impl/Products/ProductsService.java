package com.example.sample.Services.Impl.Products;

import com.example.sample.Common.Exceptions.RecordNotFoundException;
import com.example.sample.DataAccess.Abstract.Repositories.Products.ProductsDataAccess;
import com.example.sample.Services.Impl.Common.Models.SimpleResponse;
import com.example.sample.Services.Impl.Common.Statuses.Statuses;
import com.example.sample.Services.Impl.Products.Models.GetProductStatisticsResponse;
import com.example.sample.Services.Impl.Products.Models.SubmitProductRequest;

public class ProductsService implements com.example.sample.Services.Abstract.Products.ProductsService {

    private final ProductsDataAccess productsDataAccess;

    public ProductsService(ProductsDataAccess productsDataAccess) {

        this.productsDataAccess = productsDataAccess;
    }

    @Override
    public SimpleResponse SubmitProduct(SubmitProductRequest request) {
        try {
            productsDataAccess.SubmitProduct(request.Name(), request.Price(), request.PurchaseCount());
            return new SimpleResponse(Statuses.OK, "Ok!");
        } catch (Exception e) {
            return new SimpleResponse(Statuses.GENERAL_ERROR, "An error occurred!");
        }
    }

    @Override
    public GetProductStatisticsResponse GetProductStatistics(String productName) throws Exception {
        try {
            var statistics = productsDataAccess.GetProductStatistics(productName);
            return new GetProductStatisticsResponse(statistics.ProductName(), statistics.Count(), statistics.AveragePrice());
        } catch (RecordNotFoundException e) {
            throw e;
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception("General error");
        }

    }
}
