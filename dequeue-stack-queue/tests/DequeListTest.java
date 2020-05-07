import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DequeListTest {
    DequeList empty = new DequeList<>();
    DequeList one = new DequeList<>();
    DequeList three = new DequeList<>();
    DequeList many = new DequeList<>();

    @Before
    public void setUp() throws Exception {
        empty.clear();
        one.addFirst(1);
        for(int i=0; i<3;i++){
            three.addFirst(i);
        }
        for(int i=0; i<500;i++){
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
            empty.addLast(2);
            assertEquals(2,empty.size());
            one.addLast(2);
            three.addLast(3);
            many.addLast(4);
            assertEquals(2, empty.getLast());
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
    public void size() {
        assertEquals(0, empty.size());
        assertEquals(1, one.size());
        assertEquals(3, three.size());
        assertEquals(500, many.size());
    }
}