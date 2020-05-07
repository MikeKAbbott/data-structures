import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTest {
    LinkedList empty = new LinkedList<>();
    LinkedList one = new LinkedList<>();
    LinkedList three = new LinkedList<>();
    LinkedList many = new LinkedList<>();

    @Before
    public void setUp() throws Exception {
        one.addFirst(1);
        for(int i=0; i<=2;i++){
            three.addFirst(i);
        }
        for(int i=0; i<=500;i++){
            many.addFirst(i);
        }
    }

    @After
    public void tearDown() throws Exception {
        empty = null;
        one = null;
        three = null;
        many = null;
    }

    @Test
    public void clear() {
        empty.clear();
        one.clear();
        three.clear();
        many.clear();
        assertEquals(0, empty.size());
        assertEquals(0, one.size());
        assertEquals(0, three.size());
        assertEquals(0, many.size());
    }

    @Test
    public void size() {
        assertEquals(0, empty.size());
        assertEquals(1, one.size());
        assertEquals(3, three.size());
        assertEquals(501, many.size());
    }

    @Test
    public void addFirst() {
        try {
            empty.addFirst(1);
            one.addFirst(2);
            three.addFirst(3);
            many.addFirst(4);
            assertEquals(1, empty.getFirst());
            assertEquals(2, one.getFirst());
            assertEquals(3, three.getFirst());
            assertEquals(4, many.getFirst());
        }catch (NoSuchElementE E){}
    }

    @Test
    public void addLast() {
        try {
            empty.addLast(1);
            one.addLast(2);
            three.addLast(3);
            many.addLast(4);
            assertEquals(1, empty.getLast());
            assertEquals(2, one.getLast());
            assertEquals(3, three.getLast());
            assertEquals(4, many.getLast());
        }catch (NoSuchElementE E){}
    }

    @Test
    public void getFirst() {
        try {
            empty.addFirst(1);
            one.addFirst(2);
            three.addFirst(3);
            many.addFirst(4);
            assertEquals(1, empty.getFirst());
            assertEquals(2, one.getFirst());
            assertEquals(3, three.getFirst());
            assertEquals(4, many.getFirst());
        }catch (NoSuchElementE E){}
    }

    @Test
    public void getLast() {
        try {
            empty.addLast(1);
            one.addLast(2);
            three.addLast(3);
            many.addLast(4);
            assertEquals(1, empty.getLast());
            assertEquals(2, one.getLast());
            assertEquals(3, three.getLast());
            assertEquals(4, many.getLast());
        }catch (NoSuchElementE E){}
    }

    @Test
    public void removeFirst() {
        try {
            empty.addFirst(1);
            one.addFirst(2);
            three.addFirst(3);
            many.addFirst(4);
            assertEquals(1, empty.removeFirst());
            assertEquals(2, one.removeFirst());
            assertEquals(3, three.removeFirst());
            assertEquals(4, many.removeFirst());
        }catch (NoSuchElementE E){}

    }
}