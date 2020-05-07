import java.util.ArrayList;
import java.util.List;

class RootE extends Exception {
}

class NoLeftChildE extends Exception {
}

class NoRightChildE extends Exception {
}

public class WeakHeap {
    private int size;
    private ArrayList<Item> elems = new ArrayList<>();
    private ArrayList<Integer> flips = new ArrayList<>();

    WeakHeap() {
        this.size = 0;
        this.elems = new ArrayList<>();
        this.flips = new ArrayList<>();
    }

    WeakHeap(ArrayList<Item> items) {
        elems = new ArrayList<>();
        flips = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            elems.add(items.get(i));
            getElem(i).setPosition(i);
            getElem(i).setHeap(this);
            flips.add(0);
            size++;
        }
        weakHeapify();
    }

    void weakHeapify() {
        for (int i = getSize() - 1; i >= 1; i--) {
            try {
                int ancestor = getDAncestorIndex(i);
                join(ancestor, i);
            } catch (RootE rootE) {
            }
        }
    }

    // Trivial methods

    boolean isEmpty() {
        return size == 0;
    }

    int getSize() {
        return size;
    }

    Item findMin() {
        return elems.get(0);
    }

    List<Item> getElems() {
        return elems;
    }

    Item getElem(int i) {
        return elems.get(i);
    }

    int getValue(int i) {
        return elems.get(i).getValue();
    }

    int getFlip(int i) {
        return flips.get(i);
    }

    public String toString() {
        return getElems().toString();
    }

    // Computations with indices

    int getParentIndex(int i) throws RootE {
        if (i != 0) {
            return i / 2;
        }
        throw new RootE();
    }

    int getLeftChildIndex(int i) throws NoLeftChildE {
        if (i < getSize() && i != 0) {
            int index;
            index = 2 * i + getFlip(i);
            if (index < getSize()) {
                return index;
            }
        }
        throw new NoLeftChildE();
    }

    int getRightChildIndex(int i) throws NoRightChildE {
        if (i < getSize()) {
            int index;
            if (i == 0) {
                index = 2 * i + 1;
            } else {
                index = 2 * i + 1 - getFlip(i);
            }
            if (index < getSize()) {
                return index;
            }
        }
        throw new NoRightChildE();
    }

    boolean isLeftChild(int i) throws RootE {
        if(i != 0) {
            int parent = getParentIndex(i);
            if (getFlip(parent) == 0 && i % 2 == 0) {
                return true;
            } else if (getFlip(parent) == 1 && i % 2 == 1) {
                return true;
            }
        }
        return false;
    }

    boolean isRightChild(int i) throws RootE {
        if(i != 0) {
            int parent = getParentIndex(i);
            if (getFlip(parent) == 0 && i % 2 == 1) {
                return true;
            } else if (getFlip(parent) == 1 && i % 2 == 0) {
                return true;
            }
        }
        return false;
    }

    int getDAncestorIndex(int i) throws RootE {
        int parent = getParentIndex(i);
        if (isRightChild(i)) {
            return parent;
        } else {
            return getDAncestorIndex(parent);
        }
    }

    int getLeftMostChildIndex() throws NoLeftChildE {
        if (getSize() >= 2) {
            if (!elems.isEmpty()) {
                int left = 1;
                while (true) {
                    try {
                        left = getLeftChildIndex(left);
                    } catch (NoLeftChildE e) {
                        return left;
                    }
                }
            }
            throw new NoLeftChildE();
        }
        throw new NoLeftChildE();
    }

    // Helpers for main methods

    void swap(int i, int j) {
        Item first = elems.get(i);
        Item second = elems.get(j);
        elems.set(j, first);
        elems.set(i, second);
        elems.get(i).setPosition(i);
        elems.get(j).setPosition(j);
    }

    boolean join(int i, int j) {
        int firstPos = getValue(i);
        int secPos = getValue(j);
        if (secPos < firstPos) {
            swap(i, j);
            int x = getFlip(j) == 1 ? 0 : 1;
            flips.set(j,x);
            return false;
        } else return true;

    }

    void moveUp(int j) {
        try {
            int ancestor = getDAncestorIndex(j);
            boolean joined = join(ancestor, j);
            if (!joined) {
                moveUp(ancestor);
            }
        } catch (RootE e) {
        }
    }

    void moveDown(int j) {
        try {
            int leftMost = getLeftMostChildIndex();
            join(j, leftMost);
            int parent = leftMost;
            while (true) {
                join(j, parent);
                parent = getParentIndex(parent);
            }
        } catch (RootE | NoLeftChildE e) {
        }
    }

    void updateKey(int i, int value) {
        assert value < elems.get(i).getValue();
        elems.get(i).setValue(value);
        moveUp(i);
    }

    // Main methods: insert and extractMin

    void insert(Item item) {
        elems.add(item);
        size++;
        int lastIndex = getSize() - 1;
        elems.get(lastIndex).setPosition(lastIndex);
        elems.get(lastIndex).setHeap(this);
        flips.add(0);
        try {
            if (isLeftChild(lastIndex)) {
                int parentIndex = getParentIndex(lastIndex);
                flips.set(parentIndex, 0);
            }
            moveUp(getSize() - 1);
        } catch (RootE e) {
        }
    }

    Item extractMin() {
        Item min = findMin();
        int lastIndex = getSize() - 1;
        int firstIndex = 0;
        swap(firstIndex, lastIndex);
        elems.remove(lastIndex);
        flips.remove(lastIndex);
        size--;
        if (getSize() >= 1) {
            moveDown(firstIndex);
            return min;
        }
        return min;
    }

    // For debugging

    boolean checkOrder() {
        try {
            for (int i = getSize() - 1; i >= 1; i--) {
                int current = getValue(i);
                int ancestor = getDAncestorIndex(i);
                if (current < getValue(ancestor)) {
                    return false;
                }
            }
        } catch (RootE e) {
            System.out.println("Root E");
            return false;

        }
        return true;
    }

}


