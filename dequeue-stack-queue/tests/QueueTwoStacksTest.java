import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTwoStacksTest {
    QueueTwoStacks empty = new QueueTwoStacks<>();
    QueueTwoStacks one = new QueueTwoStacks<>();
    QueueTwoStacks three = new QueueTwoStacks<>();
    QueueTwoStacks many = new QueueTwoStacks<>();

    @Before
    public void setUp() throws Exception {
        empty.clear();
        one.offer(1);
        for(int i=0; i<3;i++){
            three.offer(i);
        }
        for(int i=0; i<500;i++){
            many.offer(i);
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
    public void offer() {
        try {
            empty.offer(1);
            one.offer(2);
            three.offer(3);
            many.offer(4);
            assertEquals(1, empty.poll());
            assertEquals(1, one.poll());
            assertEquals(0, three.poll());
            assertEquals(0, many.poll());
        }catch (NoSuchElementE E){}
    }

    @Test
    public void poll() {
        try {
            empty.offer(1);
            one.offer(2);
            three.offer(3);
            many.offer(4);
            assertEquals(1, empty.poll());
            assertEquals(1, one.poll());
            assertEquals(0, three.poll());
            assertEquals(0, many.poll());
        }catch (NoSuchElementE E){}
    }

    @Test
    public void remove() {
        try {
            one.remove();
            assertEquals(0,one.size());
            one.offer(2);
            one.offer(3);
            one.remove();
            assertEquals(1,one.size());
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