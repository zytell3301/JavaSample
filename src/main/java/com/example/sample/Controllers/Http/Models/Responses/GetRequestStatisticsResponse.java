package com.example.sample.Controllers.Http.Models.Responses;

import com.example.sample.Controllers.Http.Models.BaseResponse;
import com.example.sample.Controllers.Http.Models.Statuses.ResponseStatus;

public class GetRequestStatisticsResponse extends BaseResponse {
    public String ProductName;
    public Long Count;
    public Float AveragePrice;

    public GetRequestStatisticsResponse(ResponseStatus status, String message) {
        super(status, message);
    }

    public GetRequestStatisticsResponse(ResponseStatus status, String message, String productName, Long count, Float averagePrice) {
        super(status, message);
        ProductName = productName;
        Count = count;
        AveragePrice = averagePrice;
    }
}
