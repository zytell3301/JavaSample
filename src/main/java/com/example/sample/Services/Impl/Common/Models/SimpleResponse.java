package com.example.sample.Services.Impl.Common.Models;

import com.example.sample.Services.Impl.Common.Statuses.Statuses;

public record SimpleResponse(Statuses Status, String Message) {
}
