package com.example.sample.Controllers.Http.Models;

import com.example.sample.Controllers.Http.Models.Statuses.ResponseStatus;

public class BaseResponse {
    public final String Message;
    public final ResponseStatus Status;

    public BaseResponse(ResponseStatus status, String message) {

        Message = message;
        Status = status;
    }
}
