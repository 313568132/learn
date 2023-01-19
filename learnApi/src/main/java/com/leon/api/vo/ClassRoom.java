package com.leon.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClassRoom {
    public String className;
    public Book book;
    public List<Student> students;
}
