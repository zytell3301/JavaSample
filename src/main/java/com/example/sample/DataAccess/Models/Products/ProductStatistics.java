package com.example.sample.DataAccess.Models.Products;

import org.springframework.data.relational.core.mapping.Column;

public class ProductStatistics {
    @Column("Count")
    public Long Count;

    @Column("AveragePrice")
    public Float AveragePrice;
}
