package com.leon.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book implements IBookService{
    String name;
    Integer page;
}
