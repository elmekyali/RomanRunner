package com.sqli.test.romanrunner.entities;

import com.sqli.test.romanrunner.Circenses;

public class Charioteer extends Player {

    public Charioteer(String name) {
        super(name.toUpperCase().charAt(0) + "");
    }
}
