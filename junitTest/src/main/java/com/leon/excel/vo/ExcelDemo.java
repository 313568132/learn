package com.leon.excel.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ExcelDemo {
    // sku	描述	英文描述	申报品名	链接	hscode	清关国家	清关模式	品类
    @ExcelProperty("merchatSku")
    public String merchatSku;
    @ExcelProperty("itemDesc")
    public String itemDesc;
    @ExcelProperty("itemDescEn")
    public String itemDescEn;
    public String itemDescDeclear;
    public String hscode;
    @ExcelProperty("category")
    public String category;
    @ExcelProperty("itemUrl")
    public String itemUrl;
    public String remark;
    public String remark2;
    public String remark3;

}
