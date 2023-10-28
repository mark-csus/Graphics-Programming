package com.csus.csc133;

import java.util.Vector;

public class GameObjectCollection implements ICollection{

    private Vector theCollection;

    // Constructor
    public GameObjectCollection() {
        theCollection = new Vector();
    }

    public void add(GameObject newObject) {
        theCollection.addElement(newObject);
    }


    @Override
    public void add(Object newObject) {

    }

    @Override
    public IIterator getIterator() {
        return new GameVectorIterator();
    }

    // inner class
    private class GameVectorIterator implements IIterator {
        private int currElementIndex;

        public GameVectorIterator() {
            currElementIndex = -1;
        }

        public boolean hasNext() {
            if (theCollection.size() <= 0)
                return false;

            if (currElementIndex == theCollection.size() - 1)
                return false;

            return true;
        }

        public Object getNext() {
            currElementIndex++;
            return(theCollection.elementAt(currElementIndex));
        }
    }
}
