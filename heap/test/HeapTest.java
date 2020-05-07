import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
import static org.junit.Assert.*;

public class HeapTest {

    @Test
    public void sortBH() throws NoLeftChildE, NoParentE, NoRightChildE {
        ArrayList<Item> items = new ArrayList<>();
        Item it;
        it = new Item("a1", -1);
        items.add(it);

        it = new Item("a2", 3);
        items.add(it);

        it = new Item("a3", 5);
        items.add(it);

        it = new Item("a4", 38);

        items.add(it);

        it = new Item("a5", 11);
        items.add(it);

        it = new Item("a6", 37);
        items.add(it);

        it = new Item("a7", 50);
        items.add(it);

        it = new Item("a8", 41);
        items.add(it);

        it = new Item("a9", 23);
        items.add(it);

        BinaryHeap bhp;
        bhp = new BinaryHeap(items);
        TreePrinter.print(bhp.findMin());

<<<<<<< HEAD
        for (int i = 1; i < 10; i++) {
            int min = bhp.extractMin().getValue();
            System.out.println(min);
            assertEquals(i, min);
        }
=======
        for (int i = 1; i < 10; i++) assertEquals(i, bhp.extractMin().getValue());

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    @Test
    public void BHTest() throws NoLeftChildE, NoParentE, NoRightChildE {
        ArrayList<Item> items = new ArrayList<>();
        Item it;
        it = new Item("a1", 6);
        it.reverse();
        items.add(it);

        it = new Item("a2", 1);
        it.reverse();
        items.add(it);
        it = new Item("a3", 8);
        it.reverse();
        items.add(it);

        it = new Item("a4", 2);
        it.reverse();
        items.add(it);
        BinaryHeap bhp;
        bhp = new BinaryHeap(items);
        TreePrinter.print(bhp.findMin());

        for(int i = 12; i < 20; i++) {
            it = new Item("b1", i);
            bhp.insert(it);
        }
        bhp.insert(new Item("b3",9));
        bhp.insert(new Item("b3",0));
        bhp.insert(new Item("b3",3));
        bhp.insert(new Item("b4", 4));
        System.out.println(bhp.getElems());

        bhp.heapify();
        System.out.println(bhp.getElems());
        assertEquals(16,bhp.getSize());


        assertEquals(0,bhp.extractMin().getValue());
        assertEquals(1,bhp.extractMin().getValue());
        assertEquals(2,bhp.extractMin().getValue());
        assertEquals(3,bhp.extractMin().getValue());
        assertEquals(4,bhp.extractMin().getValue());
        assertEquals(6,bhp.extractMin().getValue());
        assertEquals(8,bhp.extractMin().getValue());
        assertEquals(9,bhp.extractMin().getValue());
        for(int i = 12; i <= 19; i++){
            assertEquals(i,bhp.extractMin().getValue());
        }

        for(int i = 0; i < 100; i++){
            it = new Item("b1", i);
            bhp.insert(it);
        }
        assertEquals(102,bhp.getSize());

        for(int i = 0; i < 100; i++){
            assertEquals(i,bhp.extractMin().getValue());
        }
    }
}
