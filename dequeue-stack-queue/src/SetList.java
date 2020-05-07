// Complete the implementation of this class

class SetList<E> implements SetI<E> {
    private List<E> elements;

    SetList () {

        elements = new EmptyL<>();
    }

    public void clear() {
        elements = new EmptyL<>();
    }

    public boolean isEmpty() {
        if(elements.length() == 0){
            return true;
        }else return false;

    }

    public void add(E elem) {
        if(!(elements.contains(elem))){
            elements = new NodeL<>(elem, elements);
        }

    }

    public boolean contains(E elem) {
        return elements.contains(elem);


    }

    public int size() {
        return elements.length();
    }
}
