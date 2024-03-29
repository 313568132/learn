package com.leon.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Book implements IBookService, Serializable {
    String name;
    Integer page;
}
