// Complete the implementation of this class

class StackList<E> implements StackI<E> {
    private List<E> elements;

    StackList() {
        elements = new EmptyL<>();
    }

    public void clear() {
        elements = new EmptyL<>();

    }

    public boolean empty() {
        if(elements.length() == 0){
            return true;
        }else return false;

    }

    public E peek() throws NoSuchElementE {
        return elements.getFirst();
    }

    public E pop() throws NoSuchElementE {
        E hat = elements.getFirst();
        elements = elements.getRest();
        return hat;
    }

    public void push(E item) {
        elements = new NodeL<>(item, elements);
    }
    public int size(){
        return elements.length();
    }
}
