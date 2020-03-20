package com.ju.designpatterns.iterator;

public class RealContainer implements Container {
    private String[] strings = {"one", "two", "three"};

    @Override
    public Iterator getIterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator {
        int index;

        @Override
        public boolean hasNext() {
            if (index < strings.length) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                return strings[index++];
            }
            return null;
        }
    }
}
