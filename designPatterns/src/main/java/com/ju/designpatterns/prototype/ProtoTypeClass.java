package com.ju.designpatterns.prototype;

import androidx.annotation.NonNull;

public class ProtoTypeClass implements Cloneable {
    @NonNull
    @Override
    protected Object clone() {
        ProtoTypeClass protoTypeClass = null;
        try {
            protoTypeClass = (ProtoTypeClass) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return protoTypeClass;
    }
}
