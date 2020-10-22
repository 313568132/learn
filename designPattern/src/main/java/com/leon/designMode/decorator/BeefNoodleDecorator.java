package com.leon.designMode.decorator;

public class BeefNoodleDecorator extends NoodleDecorator {

    public BeefNoodleDecorator(Noodle noodle) {
        super(noodle);
    }

    @Override
    public void cook() {
        System.out.println();
        noodle.cook();
        System.out.println("BeefNoodleDecorator");
    }
}
