package com.example.sample.DataAccess.Models.Products;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public class Product {
    @Id
    @Column("Id")
    public Long id;
    public String Name;
    public Long Price;
    public int PurchaseCount;
}
