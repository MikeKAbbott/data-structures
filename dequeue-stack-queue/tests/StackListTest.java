import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackListTest {
    StackList empty = new StackList<>();
    StackList one = new StackList<>();
    StackList three = new StackList<>();
    StackList many = new StackList<>();

    @Before
    public void setUp() throws Exception {
        one.push(1);
        for(int i=0; i<=2;i++){
            three.push(i);
        }
        for(int i=0; i<=500;i++){
            many.push(i);
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
        assertEquals(true, empty.empty());
        assertEquals(true, one.empty());
        assertEquals(true, three.empty());
        assertEquals(true, many.empty());

    }

    @Test
    public void empty() {
        assertEquals(true, empty.empty());
        assertEquals(false, one.empty());
        assertEquals(false, three.empty());
        assertEquals(false, many.empty());

    }

    @Test
    public void peek() {
        try {
            assertEquals(null, empty.peek());
            assertEquals(1, one.peek());
            assertEquals(2, three.peek());
            assertEquals(500, many.peek());
        }catch (NoSuchElementE E){}

    }

    @Test
    public void pop() {
        try {
            assertEquals(null, empty.pop());
            assertEquals(1, one.pop());
            assertEquals(2, three.pop());
            assertEquals(500, many.pop());
        }catch (NoSuchElementE E){}

    }

    @Test
    public void push() {
        empty.push(1);
        one.push(2);
        three.push(3);
        many.push(4);
        assertEquals(false, empty.empty());
        assertEquals(2, one.size());
        assertEquals(4, three.size());
        assertEquals(502, many.size());
    }

    @Test
    public void size() {
        assertEquals(0, empty.size());
        assertEquals(1, one.size());
        assertEquals(3, three.size());
        assertEquals(501, many.size());

    }
}