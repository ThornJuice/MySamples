package com.ju.designpatterns.state;

public class SecondGear extends GearState {

    SecondGear(Bike bike) {
        super(bike);
    }
    @Override
    public void gearUp() {
        System.out.println("Moving Up  from SecondGear to ThirdGear");
        bike.gearState =  new ThirdGear(bike);
    }
    @Override
    public void gearDown() {
        System.out.println("Moving Down from SecondGear to FirstGear");
        bike.gearState =  new FirstGear(bike);
    }
}
