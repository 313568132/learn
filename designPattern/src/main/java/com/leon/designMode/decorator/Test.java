package com.leon.designMode.decorator;

public class Test {
    public static void main(String[] args) {
        CircleNoodle circleNoodle = new CircleNoodle();
        circleNoodle.cook();

        BeefNoodleDecorator beefNoodleDecorator = new BeefNoodleDecorator(circleNoodle);
        beefNoodleDecorator.cook();

        SpicyNoodleDecorator spicyNoodleDecorator = new SpicyNoodleDecorator(beefNoodleDecorator);
        spicyNoodleDecorator.cook();

        SpicyNoodleDecorator spicyNoodleDecorator2 = new SpicyNoodleDecorator(spicyNoodleDecorator);
        spicyNoodleDecorator2.cook();
    }
}
