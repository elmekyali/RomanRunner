package com.sqli.test.romanrunner.entities;

public class Slot {
    private Component leftComponent;
    private Component rightComponent;

    public Slot(Component leftComponent, Component rightComponent) {
        this.leftComponent = leftComponent;
        this.rightComponent = rightComponent;
    }

    @Override
    public String toString() {
        return "|"+
                leftComponent +
                rightComponent +
                "|";
    }


    public void setLeftComponent(Component leftComponent) {
        this.leftComponent = leftComponent;
    }

    public void setRightComponent(Component rightComponent) {
        this.rightComponent = rightComponent;
    }

    public Component getRightComponent() {
        return rightComponent;
    }

    public Component getLeftComponent() {

        return leftComponent;
    }
}
