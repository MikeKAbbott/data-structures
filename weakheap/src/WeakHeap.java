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
    /*
     * Position 0 in elems is for the root.
     * The root has no left child.
     * Position 1 in elems is for the right child of the root
     * After that the left child of an item at position i is at position 2i+flips(i)
     * and the right child is at position 2i+1-flips(i)
     * The parent of a child at position i is at position i/2
     * flips(0) should never be set to 1 because that would give the root
     * a left child instead of a right one
     */

    WeakHeap() {
        this.size = 0;
        this.elems = new ArrayList<>();
        this.flips = new ArrayList<>();
    }

    /*
     * The following constructor does the following sequences of steps:
     *   - it copies the incoming items to a the array items
     *   - for each item, setPosition and setHeap are called with the
     *     appropriate parameters
     *   - for each item, a corresponding flip bit is initialized to 0
     *   - call weakHeapify
     */
    WeakHeap (ArrayList<Item> items) {
        for(int i = 0; i < items.size(); i++){
            elems.add(items.get(i));
            getElem(i).setPosition(i);
            getElem(i).setHeap(this);
            flips.add(0);
            size++;
        }
        weakHeapify();
    }

    /*
     * This method executes a loop that starts with the last element
     * in the array and ends with the element at position 1. In each
     * iteration, the item is joined with its distinguished ancestor. 
     * Note that when calling join, the distinguished ancestor is 
     * always in the first argument position. 
     */
    void weakHeapify () {
        for(int i = getSize() - 1; i > 1; i --){
            try {
                int ancestor = getDAncestorIndex(i);
                join(ancestor,i);
            } catch (RootE rootE) {}
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

    Item getElem (int i) {
        return elems.get(i);
    }

    int getValue(int i) {
        return elems.get(i).getValue();
    }

    int getFlip (int i) {
        return flips.get(i);
    }

    public String toString() {
        return getElems().toString();
    }

    // Computations with indices

    int getParentIndex(int i) throws RootE {
        if(i != 0) {
            return (i) / 2;
        }
        throw new RootE();
    }

    int getLeftChildIndex(int i) throws NoLeftChildE {
        if(i < getSize() && i != 0){
            int index;
            index = 2 * i + getFlip(i);
            if (index < getSize()) {
                return index;
            }
        }
        throw new NoLeftChildE();
    }

    int getRightChildIndex(int i) throws NoRightChildE {
        if(i < getSize()) {
            int index;
            if(i == 0){
                index = 2 * i + 1;
            }
            else {
                index = 2 * i + 1 - getFlip(i);
            }
            if (index < getSize()) {
                return index;
            }
        }
        throw new NoRightChildE();
    }

    boolean isLeftChild (int i) throws RootE {
        int parent = getParentIndex(i);
        if(getFlip(parent) == 0 && i % 2 == 0){
           return true;
       }else if(getFlip(parent) == 1 && i % 2 == 1){
           return true;
       }
       return false;
    }

    boolean isRightChild (int i) throws RootE {
        int parent = getParentIndex(i);
        if(getFlip(parent) == 0 && i % 2 == 1){
            return true;
        }else if(getFlip(parent) == 1 && i % 2 == 0){
            return true;
        }
        return false;
    }

    int getDAncestorIndex(int i) throws RootE {
        int parent = getParentIndex(i);
        if(isRightChild(i)){
            return parent;
        }else{
            return getDAncestorIndex(parent);
        }
    }

    int getLeftMostChildIndex () throws NoLeftChildE{
        if(getSize() >= 2) {
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
        elems.set(j,first);
        elems.set(i,second);
        elems.get(i).setPosition(i);
        elems.get(j).setPosition(j);
    }

    /*
     * If the value at position j is smaller than the value 
     * at position i, they are swapped and the flip bit at 
     * position j is negated. In that case the method returns 
     * false. If no action was taken, the method returns true.
     */
    boolean join (int i, int j) {
        int firstPos = getValue(i);
        int secPos = getValue(j);
        if(secPos < firstPos){
            swap(i,j);
            flips.set(j,1);
            return false;
        }else return true;
    }

    /*
     * The method starts by joining j with its distinguished ancestor. 
     * If a swap was performed, the method recursively continues by moving
     * the distinguished ancestor up. If not, the method returns immediately.
     */
    void moveUp (int j){
        try {
            int ancestor = getDAncestorIndex(j);
            boolean joined  = join(ancestor,j);
            if (!joined) {
                moveUp(ancestor);
            }
        }catch (RootE e){}
    }

    /*
     * The method starts by locating the leftmost child along the leftmost
     * spine. It then repeatedely joins j with that leftmost child and its 
     * parents. 
     */
    void moveDown (int j){
        try {
            int leftMost = getLeftMostChildIndex();
            join(j,leftMost);
            int parent = getParentIndex(leftMost);
            while (true) {
                 join(j, parent);
                 parent = getParentIndex(parent);
             }
         }catch (RootE e){} catch (NoLeftChildE noLeftChildE) {
        }
    }

    // Main methods: insert and extractMin

    /*
     * The method adds the new item at the end of the array making sure 
     * it calls setPosition and setHeap with the appropriate parameters 
     * and initializes the associated flip bit correctly. As a little 
     * optimization, if the inserted item is in a left child position, it 
     * will reset the flip bit of the parent to 0. 
     * The method then calls moveUp.
     */
    void insert (Item item){
        elems.add(item);
        size++;
        int lastIndex = getSize() - 1;
        elems.get(lastIndex).setPosition(lastIndex);
        elems.get(lastIndex).setHeap(this);
        flips.add(0);
        try {
            if(isLeftChild(lastIndex)) {
                int parentIndex = getParentIndex(lastIndex);
                flips.set(parentIndex, 0);

            }
            moveUp(getSize() - 1);
        } catch (RootE e) {}
    }

    /*
     * Like we did in the previous and as is outlined in the lecture notes, 
     * the last item in the array is moved to location 0. And then moveDown 
     * is called. Just make sure you don't call moveDown if the array has exactly 
     * one element!
     */
    Item extractMin (){
        Item min = findMin();
        int lastIndex = getSize() - 1;
        int firstIndex = 0;
        swap(firstIndex,lastIndex);
        elems.remove(lastIndex);
        flips.remove(lastIndex);
        size--;
        if(getSize() >= 2) {
            moveDown(firstIndex);
            return min;
        }
        return min;
    }

    /*
     * This method is useful for testing and debugging. It loops over the elements
     * of the array starting from the end until reaching index 1. For each item,
     * the method checks that it is larger than its distinguished ancestor.
     */
    boolean checkOrder (){
        try {
            for (int i = getSize() - 1; i >= 1; i--) {
                int current = getValue(i);
                int ancestor = getDAncestorIndex(i);
                if (current < getValue(ancestor)) {
                    return false;
                }
            }
        }catch (RootE e){
            System.out.println("Root E");
            return false;

        }
        return true;
    }
}
