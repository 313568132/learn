package com.leon.hutool;

import cn.hutool.crypto.digest.MD5;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public class StrTest {




    public static void main(String[] args) {
        String[] arr = {"0","0.027","0.1 + 77.00 GBP / 100 kg","99.00 GBP / 100 kg","0.2 + 99.00 GBP / 100 kg + 99.00 GBP" ,"0.1+9.00 GBP / 100 kg+9.00 GBP"};
        for (String str: arr){
            String result = rateMultiply100(str);
            System.out.println(str + "=================> " + result);
        }
    }

    /**
     * 获取*100的税率
     * @param str
     * @return
     */
    private static String rateMultiply100(String str) {
        // 没有判空, 空的话会报错. 理论上来说不会有空
        String result = null;
        if(isNumber(str)){
            result = new BigDecimal(str).multiply(BigDecimal.valueOf(100)).stripTrailingZeros().toPlainString();
        }else{
            if(str.contains("+")){
                String[] split = str.split("\\+");
                StringBuilder sb = new StringBuilder();
                for (String sp : split) {
                    String spTrim = sp.trim();
                    if(isNumber(spTrim)){
                        String rate = new BigDecimal(spTrim).multiply(BigDecimal.valueOf(100))
                                .stripTrailingZeros().toPlainString();
                        sb.append(rate + " + ");
                    }else{
                        sb.append(spTrim + " + ");
                    }
                }
                int indexOf = sb.lastIndexOf(" + ");
                result = sb.substring(0, indexOf);
            }else{
                result = str;
            }
        }
        return result;
    }


    /**
     * 判断一个字符串是否是数字, 包含小数
     * @param number
     * @return
     */
    public static boolean isNumber(String number){
        if(StringUtils.isBlank(number)){
            return false;
        }
        int index = number.indexOf(".");
        if(index<0){
            return StringUtils.isNumeric(number);
        }else{
            String num1 = number.substring(0,index);
            String num2 = number.substring(index+1);
            return StringUtils.isNumeric(num1) && StringUtils.isNumeric(num2);
        }
    }

}
