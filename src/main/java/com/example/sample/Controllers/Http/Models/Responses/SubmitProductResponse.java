package com.example.sample.Controllers.Http.Models.Responses;

import com.example.sample.Controllers.Http.Models.BaseResponse;
import com.example.sample.Controllers.Http.Models.Statuses.ResponseStatus;

public class SubmitProductResponse extends BaseResponse {
    public SubmitProductResponse(ResponseStatus status, String message) {
        super(status, message);
    }
}
