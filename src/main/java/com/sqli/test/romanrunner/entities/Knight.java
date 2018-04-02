package com.sqli.test.romanrunner.entities;

public class Knight extends Player {
    public Knight(String name) {
        super(name.toUpperCase().charAt(0) + "");
    }
}
