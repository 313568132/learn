package com.leon.designMode.decorator;

public abstract class NoodleDecorator implements Noodle {

    public Noodle noodle;

    public NoodleDecorator(Noodle noodle){
        this.noodle = noodle;
    }

    @Override
    public void cook() {
        noodle.cook();
    }
}
