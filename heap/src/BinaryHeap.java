import java.util.ArrayList;
import java.util.List;

/**
 * Binary heap with reverse bits...
 * We can flip left and right subtrees in one operation
 * <p>
 * There is a subtle interaction between the heap and the items it contains.
 * - the heap maintains an arraylist of all items
 * - each item has a reference to the heap and its position within the arraylist
 */

class NoParentE extends Exception {
}

class NoLeftChildE extends Exception {
}

class NoRightChildE extends Exception {
}

public class BinaryHeap {
    private int size;
    private ArrayList<Item> elems = new ArrayList<>();

    BinaryHeap() {
        this.size = 0;
        this.elems = new ArrayList<>();
    }

    /**
     * This constructor performs "heapify". First it copy the incoming
     * elements one-by-one to the arraylist 'elems' stored as an instance variable.
     * For each item copied, the constructor should initialize properly using
     * setPosition and setHeap. When everything is properly initialized and
     * copied to 'elems' the constructor calls 'heapify'.
     */
<<<<<<< HEAD
    BinaryHeap(ArrayList<Item> es){
        for (int i = 0; i < es.size(); i++) {
            elems.add(es.get(i));
            getElem(i).setPosition(i);
            getElem(i).setHeap(this);
            size++;
        }
=======
    BinaryHeap(ArrayList<Item> es) {
        this.size = 0;
        this.elems = new ArrayList<>();
        size = es.size();
        for (int i = 0; i < es.size(); i++) {
            Item old = es.get(i);
            old.setPosition(i);
            old.setHeap(this);
            elems.add(old);
        }

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
        heapify();
    }

    /**
     * Implement it as discussed in class...
     */
    void heapify() {
<<<<<<< HEAD
        int halfSize = getSize() / 2 - 1;
        for (int i = halfSize; i >=0; i--) {
=======

        for (int i = size / 2 - 1; i >= 0; i--) {
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
            moveDown(i);
        }
    }

    boolean isEmpty() {
        return size == 0;
    }

    int getSize() {
        return size;
    }

    /**
     * We will location 0 in the array. The minimum is always guaranteed to be there
     * unless of course the array is empty
     */
    Item findMin() {
<<<<<<< HEAD
=======
        // TODO
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
        return elems.get(0);
    }

    List<Item> getElems() {
        return elems;
    }

    Item getElem(int i) {
        return elems.get(i);
    }

    /**
     * As discussed in class and in the notes, the parent is at index i/2
     * unless of course the current node is the root of the tree
     */
    int getParentIndex(int i) throws NoParentE {
<<<<<<< HEAD
        if(i > 0){
            return (i-1)/2;
        }else{
            throw new NoParentE();
        }
=======
        // TODO
        if (i != 0) {
            return (i - 1) / 2;
        }

        throw new NoParentE();
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    /**
     * Under normal circumstances the left child is at index 2i+1. It is possible
     * the index 2i+1 is outside of the array bounds and in that case the node
     * does not have a left child. It is also possible that the current element
     * has its reverse bit set, which means that the child at index 2i+1 is actually
     * the right child and the child at index 2i+2 is the left child.
     */

    int getLeftChildIndex(int i) throws NoLeftChildE {
<<<<<<< HEAD
        if(i < getSize()) {
            int index;
            if (getElem(i).getRevbit() == 1) {
                index = 2 * i + 2;
            } else {
                index = 2 * i + 1;
            }
            if (index < getSize()) {
                return index;
            }
=======
        // check bounds
        // check reverse bit
        if (!elems.isEmpty()) {
            if (this.getElem(i).getRevbit() == 0) {

                if (2 * i + 1 < size) {


                    return 2 * i + 1;

                }
            }

            if (this.getElem(i).getRevbit() == 1) {

                if (2 * i + 2 < size) {


                    return 2 * i + 2;

                }
            }


>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
        }
        throw new NoLeftChildE();
    }

    int getRightChildIndex(int i) throws NoRightChildE {
<<<<<<< HEAD
        if(i < getSize()) {
            int index;
            if (getElem(i).getRevbit() == 1) {
                index = 2 * i + 1;
            } else {
                index = 2 * i + 2;
            }
            if (index < getSize()) {
                return index;
            }
        }
=======

        if (!elems.isEmpty()) {
            if (this.getElem(i).getRevbit() == 0) {

                if (2 * i + 2 < size) {


                    return 2 * i + 2;

                }
            }

            if (this.getElem(i).getRevbit() == 1) {

                if (2 * i + 1 < size) {


                    return 2 * i + 1;

                }
            }
        }

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
        throw new NoRightChildE();
    }

    /**
     * This method swaps the array entries at the given indices. It also needs
     * to update each element with its new position.
     */
    void swap(int i, int j) {
<<<<<<< HEAD
        Item firstItem = getElem(i);
        Item secondItem = getElem(j);
        elems.set(i, secondItem);
        elems.set(j, firstItem);
        getElem(i).setPosition(i);
        getElem(j).setPosition(j);
=======

        Item oldI = this.getElem(i);
        Item oldJ = this.getElem(j);

        //set i to j and j to i
        oldI.setPosition(j);
        oldJ.setPosition(i);

        elems.set(j, oldI);
        elems.set(i, oldJ);

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    int getValue(int i) {
        return getElem(i).getValue();
    }

    /**
     * When an element is inserted, it is inserted in the bottom layer of the
     * tree and then moveUp is recursively called to move it to its proper
     * position.
     */
<<<<<<< HEAD
    void moveUp(int i) throws NoParentE {
        int current = getValue(i);
        int parentIndex = getParentIndex(i);
        int parent = getValue(parentIndex);
        if (current < parent) {
            swap(i, parentIndex);
            moveUp(parentIndex);
        }
    }

    void insert(Item ek){
        elems.add(ek);
        size++;
        try {
            moveUp(getSize() - 1);
        } catch (NoParentE e) {}
=======
    void moveUp(int i) {
        try {
            int parentIndex = getParentIndex(i);

            swap(parentIndex, i);

            moveUp(parentIndex);

        } catch (NoParentE e) {


        }
    }

    void insert(Item ek) {

        ek.setPosition(size);
        elems.add(ek);
        moveUp(size);
        size++;

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    /**
     * When a large element finds itself high in the tree for some reason,
     * we need to move it down. For that we need to compare it to both its
     * children and swap it with the smaller of them
     */
    int minChildIndex(int i) throws NoLeftChildE {
<<<<<<< HEAD
        int left = getLeftChildIndex(i);
        try {
            int right = getRightChildIndex(i);
            int childIndex;
            if (getValue(left) < getValue(right)) {
                childIndex = left;
            } else {
                childIndex = right;
            }
            return childIndex;
        } catch (NoRightChildE e) {
            return left;
        }
    }

    void moveDown(int i){
        try {
            int minChild = minChildIndex(i);
            int item = getValue(i);
            if (item > getValue(minChild)) {
                swap(i, minChild);
                moveDown(minChild);
                System.out.println("swap made");
            }
        }catch (NoLeftChildE e){
=======

        int leftChildIndex = getLeftChildIndex(i);
        try {
            int rightChildIndex = getRightChildIndex(i);
            if(elems.get(leftChildIndex).getValue()< elems.get(rightChildIndex).getValue()){
                return leftChildIndex;
            }else{
                return rightChildIndex;
            }
        } catch(NoRightChildE e){
            return leftChildIndex;
        }

    }

    void moveDown(int i) {
        try {
            int minChildIndex = minChildIndex(i);
            if(elems.get(minChildIndex).getValue() < elems.get(i).getValue()) {
                swap(i, minChildIndex);
                moveDown(minChildIndex);
            }
        } catch (NoLeftChildE noLeftChildE) {
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9

        }
    }

    /**
     * The minimum is at location 0. To remove it we take the last element
     * in the array and move it to location 0 and then recursively apply
     * moveDown.
     */
    Item extractMin() {
<<<<<<< HEAD
        Item min = findMin();
        swap(0,getSize()-1);
        elems.remove(getSize()-1);
        size--;
        moveDown(0);
=======
        // TODO
        Item min = elems.get(0);
        int lastElementIndex = size-1;
        swap(lastElementIndex,0);
        elems.remove(size-1);
        size--;
        moveDown(0);

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
        return min;
    }

    public String toString() {
        return getElems().toString();
    }
}
