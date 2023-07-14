package com.example.sample.Controllers.Http.Models.Requests;

import com.example.sample.Controllers.Http.Models.BaseRequest;

public class SubmitProductRequest extends BaseRequest {
    public String Name;
    public Long Price;
    public int PurchaseCount;
}
