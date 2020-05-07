import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;

public class DynamicArrayTest {
    private DynamicArray<Integer> d;

    @Before
    public void setUp() throws Exception {
        d = new DynamicArray<>(5);
    }

    @After
    public void tearDown() throws Exception {
        d = null;
    }
    @Test
    public void clear(){
        assertEquals(0,d.size());
        d.addFirst(1);
        d.addFirst(2);
        assertEquals(2,d.size());
        d.clear();
        assertEquals(0,d.size());
        d.addLast(1);
        d.addLast(2);
        d.clear();
        assertEquals(0,d.size());
    }

    @Test
    public void size() {
        assertEquals(0, d.size());
        d.addFirst(1);
        assertEquals(1, d.size());
        d.addLast(3);
        assertEquals(2,d.size());
        try {
            d.removeFirst();
        }catch(NoSuchElementE E){
            System.out.println("List is empty");
        };
        assertEquals(1,d.size());
        try {
            d.removeLast();
        }catch(NoSuchElementE E){
            System.out.println("List is empty");

        };
        assertEquals(0,d.size());

    }
    @Test
    public void push(){
        d.push(1);
        assertEquals(1,d.size());
        d.push(2);
        d.push(4);
        assertEquals(3,d.size());
        d.push(5);
        d.push(6);
        assertEquals(5,d.size());
        d.push(7);
    }
    @Test
    public void peek(){
        try{
            d.push(1);
            d.push(2);
            d.push(3);
            assertTrue(d.peek() == 3);
            d.push(4);
            d.push(5);
            d.push(6);
            assertTrue(d.peek() == 6);

        }catch (NoSuchElementE E){
            System.out.println("List is empty");
        };
    }
    @Test
    public void pop(){
        try {
            d.push(1);
            assertTrue(d.pop() == 1);
            d.push(2);
            d.push(4);
            assertTrue(d.pop() == 4);
            d.push(5);
            d.push(6);
            d.pop();
            assertTrue(d.pop() == 5);


        }catch (NoSuchElementE E){
            System.out.println("List is empty");

        };
    }
    @Test
    public void offer(){
        d.offer(1);
        assertEquals(1,d.size());
        d.offer(2);
        d.offer(4);
        assertEquals(3,d.size());
        d.clear();
        d.offer(3);
        assertEquals(1,d.size());
        d.offer(4);
        d.offer(5);
        d.offer(7);
        d.offer(8);
        d.offer(3);
        d.offer(1);
        System.out.println(d.getCapacity());
    }
    @Test
    public void poll(){
        try {
            assertTrue(d.poll() == 1);
        }catch (NoSuchElementE E){
            System.out.println("List is empty");
        }
    };
    @Test
    public void remove(){
        d.push(1);
        d.push(2);
        try {
            assertEquals(Optional.of(2), d.remove());
        }catch (NoSuchElementE E){
            System.out.println("List is empty");

        };
    }
    @Test
    public void addFirst(){
        d.addFirst(1);
        d.addFirst(3);
        d.addFirst(4);
        d.addFirst(5);
        d.addFirst(2);
        assertEquals(5,d.size());
        d.addFirst(1);
        d.addFirst(3);
        d.addFirst(4);
        d.addFirst(5);
        d.addFirst(2);
        assertEquals(10,d.size());
    }
    @Test
    public void addLast(){
        d.addLast(1);
        d.addLast(2);
        d.addLast(3);
        d.addLast(4);
        d.addLast(5);
        assertEquals(5,d.size());
        assertFalse(d.size() == 4);
        d.addLast(6);
        d.addLast(7);
        d.addLast(8);
        d.addLast(9);
        d.addLast(10);
        assertEquals(10,d.size());
        assertFalse(d.size() == 9);


    }
    @Test
    public void getFirst(){
        try{
            d.addFirst(1);
            assertTrue(d.getFirst() == 1);
            d.addFirst(2);
            d.addFirst(4);
            assertTrue(d.getFirst() == 4);
            d.addFirst(3);
            d.addFirst(9);
            assertTrue(d.getFirst() == 9);
            d.addFirst(4);
            d.removeFirst();
            assertEquals(4,(int)d.getFirst());

        }catch (NoSuchElementE E){
            System.out.println("List is empty");
        }
    }
    @Test
    public void getLast(){
        try{
            d.addLast(1);
            assertEquals(1,(int)d.getLast());
            d.removeLast();


        }catch (NoSuchElementE E){
            System.out.println("List is empty");

        }
    }
    @Test
    public void removeFirst(){
        try{
            d.addFirst(4);
            assertEquals(4,(int) d.removeFirst());
            d.addFirst(5);
            d.addFirst(6);
            assertEquals(6,(int) d.removeFirst());
            assertEquals(5,(int) d.removeFirst());


        }catch (NoSuchElementE E){
            System.out.println("List is empty");
        }
    }
    @Test
    public void removeLast(){
        try{
            d.addLast(6);
            assertTrue(d.removeLast() == 6);
            d.addLast(7);
            d.addLast(8);
            d.addLast(9);
            assertTrue(d.removeLast() == 9);
            d.addLast(1);
            d.addLast(2);
            d.addLast(4);
            d.addLast(9);
            d.addLast(8);
            d.addLast(2);
            assertTrue(d.removeLast() == 2);

        }catch (NoSuchElementE E){
            System.out.println("List is empty");
        }
    }
    @Test
    public void dequeNoResize() throws NoSuchElementE {
        assertEquals(0, d.size());
        d.addFirst(1);
        d.addFirst(2);
        d.addFirst(3);
        assertEquals(3, (int) d.removeFirst());
        assertEquals(1, (int) d.removeLast());
        assertEquals(2, (int) d.removeLast());
        assertEquals(0, d.size());
        d.addLast(1);
        d.addLast(2);
        d.addFirst(3);
        d.addLast(4);
        d.addFirst(5);
        assertEquals(5, (int) d.removeFirst());
        assertEquals(3, (int) d.removeFirst());
        assertEquals(1, (int) d.removeFirst());
        assertEquals(2, (int) d.removeFirst());
        assertEquals(4, (int) d.removeFirst());
    }

    @Test
    public void dequeResize() throws NoSuchElementE {
        d.addLast(1);
        d.addLast(2);
        d.addFirst(3);
        d.addLast(4);
        d.addFirst(5);
        d.addFirst(6);
        assertEquals(10, d.getCapacity());
        assertEquals(6, (int) d.removeFirst());
        assertEquals(5, (int) d.removeFirst());
        assertEquals(3, (int) d.removeFirst());
        assertEquals(1, (int) d.removeFirst());
        assertEquals(2, (int) d.removeFirst());
        assertEquals(4, (int) d.removeFirst());
    }
}
