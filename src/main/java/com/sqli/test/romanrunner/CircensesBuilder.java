package com.sqli.test.romanrunner;

import com.sqli.test.romanrunner.entities.*;

import java.util.*;

public class CircensesBuilder {

    private boolean isInRight       = false ;
    private boolean isInLeft        = true  ;
    private boolean moveToNextSlot  = true  ;

    List<Slot> route ;

    public CircensesBuilder() {
        route = new LinkedList<Slot>();
        Slot firstSlot = new Slot(new EmptySlot() , new EmptySlot());
        route.add(firstSlot);
    }

    public CircensesBuilder addCoin() {
        Slot newSlot = null;

        if(moveToNextSlot) {
            if (isInLeft) {
                newSlot = new Slot(new Coin(), new EmptySlot());
            } else if (isInRight) {
                newSlot = new Slot(new EmptySlot(), new Coin());
            }
            route.add(newSlot);
        }else{
            if (isInLeft) route.get(route.size() - 1).setLeftComponent(new Coin());
            else if (isInRight) route.get(route.size() - 1).setRightComponent(new Coin());
            moveToNextSlot = true;
        }
        return this;
    }

    public CircensesBuilder addEmptySlot() {
        Slot newSlot = null;
        if(moveToNextSlot) {
            if (isInLeft) {
                newSlot = new Slot(new EmptySlot(), new EmptySlot());
            } else if (isInRight) {
                newSlot = new Slot(new EmptySlot(), new EmptySlot());
            }
            route.add(newSlot);
        }else{
            if (isInLeft) route.get(route.size() - 1).setLeftComponent(new EmptySlot());
            else if (isInRight) route.get(route.size() - 1).setRightComponent(new EmptySlot());
            moveToNextSlot = true;
        }
        return this;
    }

    public CircensesBuilder addObstacle() {
        Slot newSlot = null;
        if(moveToNextSlot) {
            if (isInLeft) {
                newSlot = new Slot(new Obstacle(), new EmptySlot());
            } else if (isInRight) {
                newSlot = new Slot(new EmptySlot(), new Obstacle());
            }
            route.add(newSlot);
        }else{
            if (isInLeft) route.get(route.size() - 1).setLeftComponent(new Obstacle());
            else if (isInRight) route.get(route.size() - 1).setRightComponent(new Obstacle());
            moveToNextSlot = true;
        }
        return this;
    }

    public Circenses build() {
        Slot lastSlot = new Slot(new FinalLine() , new FinalLine());
        route.add(lastSlot);
        return new Circenses(route);
    }


    public CircensesBuilder right() {
        isInRight = true;
        isInLeft  = false;
        moveToNextSlot = false;
        return this;
    }

    public CircensesBuilder left() {
        isInRight = false;
        isInLeft  = true;
        moveToNextSlot = false;
        return this;
    }
}
