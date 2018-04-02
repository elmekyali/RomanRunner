package com.sqli.test.romanrunner;

import com.sqli.test.romanrunner.entities.Slot;

import java.util.*;

public class Circenses {


    List<Slot> route;

    public Circenses(List<Slot> route) {
        this.route = route;
    }

    public List<Slot> getRoute() {
        return route;
    }


    public String draw() {
        String out = "";
        out += route.get(0).toString();

        for (int index = 1 ; index < route.size() ; index++)
            out = route.get(index).toString() + "\n" + out ;

        return out;
    }
}
