package com.leon.api.vo;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
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
