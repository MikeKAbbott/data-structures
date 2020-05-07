import java.lang.reflect.Array;
import java.net.SocketOption;
import java.util.Arrays;
import java.util.Optional;

public class DynamicArray<E> implements StackI<E>, QueueI<E>, DequeI<E> {
    private Optional<E>[] elements;
    private int capacity, front, back, size;
    //
    // data stored in locations:
    // front+1, front+2, ... back-2, back-1 (all mod capacity)
    //
    // common cases:
    // front points to an empty location
    // back points to an empty location
    // adding to front decreases 'front' by 1
    // adding to back increases 'back' by 1
    // removing does the opposite
    //
    // |-------------------------|
    // | 4 5 6 _ _ _ _ _ _ 1 2 3 |
    // |-------------------------|
    //         /\        /\      /\
    //        back      front  capacity
    //

    @SuppressWarnings("unchecked")
    DynamicArray (int initialCapacity) {
        elements = (Optional<E>[]) Array.newInstance(Optional.class, initialCapacity);
        Arrays.fill(elements, Optional.empty());
        capacity = initialCapacity;
        front = capacity - 1;
        back = 0;
        size = 0;
    }

    // Complete the definitions of the following methods from the interfaces
    public void clear(){
        elements = (Optional<E>[]) Array.newInstance(Optional.class, capacity);
        Arrays.fill(elements, Optional.empty());
        size = 0;
    }
    public int size () {
        return size;
    }
    //stack methods
    public void push(E item) {
<<<<<<< HEAD
        if(size == capacity) doubleCapacity();
        elements[front] = Optional.of(item);
        front = Math.floorMod(front - 1,capacity);
        size++;
=======
	
>>>>>>> bc17e900c22d50e60d3f18b44a1c4637f12fe25d
    }

    public E peek() throws NoSuchElementE {
        if(size() == 0) throw new NoSuchElementE();
        return (E) elements[front + 1].get();
    }
    public E pop() throws NoSuchElementE {
        if(size() == 0) throw new NoSuchElementE();
        E hat = elements[front + 1].get();
        elements[front] = Optional.empty();
        front = Math.floorMod(front + 1,capacity);
        size--;
        return hat;
    }
    //queue methods
    public void offer(E elem) {
        if(size == capacity) doubleCapacity();
        elements[back] = Optional.of(elem);
        size++;
    }

    public E poll() throws NoSuchElementE {
        if(size() == 0) {
            throw new NoSuchElementE();
        };
        E hat = elements[front].get();
        return hat;
    }

    public E remove() throws NoSuchElementE {
        if(size() == 0) {throw new NoSuchElementE();};
        E hat = (E) elements[front];
        elements[front] = elements[front - 1];
        size--;
        return hat;
    }
    //dequeue methods
    public void addFirst(E elem) {
        if(size == capacity){
            doubleCapacity();
        }
        elements[front] = Optional.of(elem);
        front = Math.floorMod(front - 1,capacity);
        size++;
    }
    public void addLast(E elem) {
        if(size == capacity) {
            doubleCapacity();
        }
        elements[back] = Optional.of(elem);
        front = Math.floorMod(back + 1,capacity);
        size++;
    }

    public E getFirst() throws NoSuchElementE {
        if(size() == 0) throw new NoSuchElementE();
        return peek();
    }
    public E getLast() throws NoSuchElementE {
        if(size() == 0) throw new NoSuchElementE();
        return elements[back].get();
    }

    public E removeFirst() throws NoSuchElementE {
        if(size() == 0)throw new NoSuchElementE();
        E hat = elements[front + 1].get();
        elements[front + 1] = Optional.empty();
        front = Math.floorMod(front + 1,capacity);
        size--;
        return hat;
    }

    public E removeLast() throws NoSuchElementE {
        if(size() == 0) throw new NoSuchElementE();
        E shoe = elements[back - 1].get();
        elements[back - 1] = Optional.empty();
        size--;
        back = (back - 1 + elements.length) % elements.length;
        return shoe;

    }
    public void doubleCapacity(){
        Optional<E>[] new_elements = (Optional<E>[]) Array.newInstance(Optional.class, capacity*2);
        Arrays.fill(new_elements, Optional.empty());
        for(int i=0; i < (capacity - 1);i++){
            new_elements[i] = elements[(front + i) % elements.length];
        }
        elements = new_elements;
        front = capacity - 1;
        back = 0;
        capacity = capacity * 2;
    }

    // the following methods are used for debugging and testing;
    // please do not edit them

    Optional<E>[] getElements () { return elements; }

    int getCapacity () { return capacity; }

    int getFront () { return front; }

    int getBack () { return back; }

}
