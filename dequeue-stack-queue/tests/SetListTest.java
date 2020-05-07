import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SetListTest {
    SetList empty = new SetList<>();
    SetList one = new SetList<>();
    SetList three = new SetList<>();
    SetList many = new SetList<>();
    @Before
    public void setUp() throws Exception {
        one.add(1);
        for(int i=0; i<=2;i++){
            three.add(i);
        }
        for(int i=0; i<=500;i++){
            many.add(i);
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
        assertEquals(true, empty.isEmpty());
        assertEquals(true, one.isEmpty());
        assertEquals(true, three.isEmpty());
        assertEquals(true, many.isEmpty());
    }

    @Test
    public void isEmpty() {
        assertEquals(true, empty.isEmpty());
        assertEquals(false, one.isEmpty());
        assertEquals(false, three.isEmpty());
        assertEquals(false, many.isEmpty());
    }

    @Test
    public void add() {
        empty.add(1);
        one.add(4);
        three.add(5);
        many.add("hello");
        assertEquals(true,empty.contains(1));
        assertEquals(true,one.contains(4));
        assertEquals(true,three.contains(5));
        assertEquals(true,many.contains("hello"));

    }

    @Test
    public void contains() {
        empty.add(1);
        one.add(4);
        three.add(5);
        many.add("hello");
        assertEquals(true,empty.contains(1));
        assertEquals(true,one.contains(4));
        assertEquals(true,three.contains(5));
        assertEquals(true,many.contains("hello"));
    }

    @Test
    public void size() {
        assertEquals(0, empty.size());
        assertEquals(1, one.size());
        assertEquals(3, three.size());
        assertEquals(501, many.size());

    }
}