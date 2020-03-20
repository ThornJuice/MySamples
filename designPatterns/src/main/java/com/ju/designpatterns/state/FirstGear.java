package com.ju.designpatterns.state;

public class FirstGear extends GearState {

    FirstGear(Bike bike) {
        super(bike);
    }

    @Override
    public void gearUp() {
        System.out.println("Moving Up from FirstGear to SecondGear");
        bike.gearState =  new SecondGear(bike);
    }
    @Override
    public void gearDown() {
        System.out.println("Already in the FirstGear - cannot go lower");
    }
}
