package com.leon.designMode.builder;

import cn.hutool.core.lang.Console;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;


@Data
public class Computer {
    private String cpu;//必须
    private String ram;//必须
    private int usbCount;//可选
    private String keyboard;//可选
    private String display;//可选

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.usbCount = builder.usbCount;
        this.keyboard = builder.keyboard;
        this.display = builder.display;
    }

    static class Builder{
        private String cpu;//必须
        private String ram;//必须
        private int usbCount;//可选
        private String keyboard;//可选
        private String display;//可选

        public Builder(String cpu,String ram) {
            this.cpu = cpu;
            this.ram = ram;
        }

        public Builder setUsbCount(int usbCount) {
            this.usbCount = usbCount;
            return this;
        }

        public Builder setKeyboard(String keyboard) {
            this.keyboard = keyboard;
            return this;
        }

        public Builder setDisplay(String display) {
            this.display = display;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }


}
class Test{
    public static void main(String[] args) {
        Computer computer = new Computer.Builder("cpu", "ram")
                .setDisplay("Display")
                .setUsbCount(4)
                .setKeyboard("Keyboard")
                .build();
        System.out.println(computer);
        Console.log(computer);

        System.out.println(StringUtils.isNumeric("25"));
        System.out.println(StringUtils.isNumeric("25.2"));
    }
}