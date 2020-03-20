package com.ju.designpatterns.state;


public abstract class GearState {
    Bike bike;
    GearState(Bike bike){
        this.bike = bike;
    }
    public abstract void gearUp();
    public abstract void gearDown();
}