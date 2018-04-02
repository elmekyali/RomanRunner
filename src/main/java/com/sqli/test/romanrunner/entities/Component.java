package com.sqli.test.romanrunner.entities;

public abstract class Component {
    protected String range;

    public Component(String range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return range;
    }
}
