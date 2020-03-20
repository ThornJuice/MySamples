package com.ju.designpatterns.state;

public class ThirdGear extends GearState{
    public ThirdGear(Bike bike) {
        super(bike);
    }
    @Override
    public void gearUp() {
        System.out.println("Already in the ThirdGear - cannot go higher");
    }
    @Override
    public void gearDown() {
        System.out.println("Moving Down from ThirdGear to SecondGear");
        bike.gearState =  new SecondGear(bike);
    }
}
