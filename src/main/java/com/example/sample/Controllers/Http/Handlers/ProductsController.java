package com.example.sample.Controllers.Http.Handlers;

import com.example.sample.Common.Exceptions.RecordNotFoundException;
import com.example.sample.Controllers.Http.Models.Requests.SubmitProductRequest;
import com.example.sample.Controllers.Http.Models.Responses.GetRequestStatisticsResponse;
import com.example.sample.Controllers.Http.Models.Responses.SubmitProductResponse;
import com.example.sample.Controllers.Http.Models.Statuses.ResponseStatus;
import com.example.sample.Services.Abstract.Products.ProductsService;
import com.example.sample.Services.Impl.Common.Statuses.Statuses;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping(path = "/products")
public class ProductsController {
    public static void main(String[] args) {
        SpringApplication.run(ProductsController.class, args);
    }

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {

        this.productsService = productsService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "submit")
    public SubmitProductResponse SubmitProduct(@RequestBody SubmitProductRequest request) {
        var result = productsService.SubmitProduct(new com.example.sample.Services.Impl.Products.Models.SubmitProductRequest(request.Name, request.Price, request.PurchaseCount));
        if (result.Status().equals(Statuses.OK)) {
            return new SubmitProductResponse(ResponseStatus.OK, "Successful");
        }
        return new SubmitProductResponse(ResponseStatus.GENERAL_ERROR, "GeneralError!");
    }

    @RequestMapping(method = RequestMethod.GET, path = "get-statistics")
    public GetRequestStatisticsResponse GetRequestStatistics(@RequestParam("name") String name) {
        try {
            var statistic = productsService.GetProductStatistics(name);
            return new GetRequestStatisticsResponse(ResponseStatus.OK, "Ok!", statistic.ProductName(), statistic.Count(), statistic.AveragePrice());
        } catch (RecordNotFoundException e) {
            return new GetRequestStatisticsResponse(ResponseStatus.DATA_NOT_FOUND, "Product not found!");
        } catch (Exception e) {
            return new GetRequestStatisticsResponse(ResponseStatus.GENERAL_ERROR, "Error!");
        }
    }
}
