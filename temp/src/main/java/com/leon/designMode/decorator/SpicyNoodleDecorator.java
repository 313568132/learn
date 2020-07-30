package com.leon.designMode.decorator;

public class SpicyNoodleDecorator extends NoodleDecorator {

    public SpicyNoodleDecorator(Noodle noodle) {
        super(noodle);
    }

    @Override
    public void cook() {
        super.cook();
        System.out.println("SpicyNoodleDecorator");
    }
}
