// Complete the implementation of this class
// For this implementation all the methods should take amortized O(1) time

class QueueTwoStacks<E> implements QueueI<E> {
    private StackList<E> front, back;

    QueueTwoStacks () {
        front = new StackList<>();
        back = new StackList<>();
    }

    public void clear() {
        front = new StackList<>();
        back = new StackList<>();
    }

    public void offer(E elem) {
        if(!(front.empty())) {
            back.push(elem);
        }else front.push(elem);


    }

    public E poll() throws NoSuchElementE {
        return front.peek();
    }

    public E remove() throws NoSuchElementE {
        E hat = front.peek();
        while(!front.empty()){
            front.pop();
        }
        if(front.empty()) {
          front.push(back.pop());
        }
        return hat;
    }

    public int size() {
        return (front.size() + back.size());
    }
}
