import org.junit.Test;
import java.util.Random;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HashTableTest {
    @Test
    public void hashSeparateChaining () {
        Random r = new Random(1);
        HashFunction hf = new HashUniversal(r,31, 10);
        HashTable ht = new HashSeparateChaining(hf);
        ht.insert(4);
        ht.insert(2);
        ht.insert(12);
        ht.insert(1);
        ht.insert(0);
        assertTrue(ht.search(1));
        System.out.println(ht);
        ht.delete(12);
        ht.delete(2);
        System.out.println(ht);
        assertFalse(ht.search(6));
        assertTrue(ht.search(1));
        assertFalse(ht.search(2));
        ht.insert(22);
        System.out.println("Before rehashing");
        System.out.println(ht);
        ht.rehash();
        System.out.println(ht.getCapacity());
        System.out.println("After rehashing");
        System.out.println(ht);
    }

    @Test
    public void fig55 () {

        HashFunction hf = new HashMod(10);
        HashTable ht = new HashSeparateChaining(hf);
        ht.insert(0);
        ht.insert(81);
        ht.insert(64);
        ht.insert(49);
        ht.delete(0);
        ht.delete(81);
        ht.delete(64);
        ht.delete(49);
        System.out.println("Checking for delete...");
        System.out.println(ht);
        assertFalse(ht.search(0));
        assertFalse(ht.search(81));
        assertFalse(ht.search(64));
        assertFalse(ht.search(49));
        ht.insert(9);
        ht.insert(36);
        ht.insert(25);
        ht.insert(16);
        assertTrue(ht.search(9));
        assertTrue(ht.search(36));
        assertTrue(ht.search(25));
        assertTrue(ht.search(16));
        ht.insert(4);
        ht.insert(1);
        System.out.println("Fig. 5.5");
        System.out.println(ht);
    }
    @Test
    public void fig56 () {

        HashFunction hf = new HashMod(10);
        HashTable ht = new HashSeparateChaining(hf);
        ht.insert(0);
        ht.insert(81);
        ht.insert(64);
        ht.insert(49);
        ht.insert(9);
        ht.insert(36);
        ht.insert(25);
        ht.insert(16);
        ht.insert(4);
        ht.insert(1);
        System.out.println("Fig. 5.5");
        System.out.println(ht);
    }

    @Test
    public void fig511 () {
        HashFunction hf = new HashMod(10);
        HashTable ht = new HashLinearProbing(hf);
        ht.insert(89);
        ht.insert(18);
        ht.insert(49);
        ht.insert(58);
        ht.insert(69);
        ht.insert(73);
        ht.insert(2);
        ht.insert(6);
        ht.delete(6);
        ht.delete(69);
        ht.delete(49);
        ht.delete(89);
        assertTrue(ht.search(2));
        assertFalse(ht.search(6));
        assertTrue(ht.search(58));
        assertFalse(ht.search(89));
        System.out.println("Fig. 5.11");
        System.out.println(ht);
        assertTrue(ht.search(73));
        assertFalse(ht.search(69));
        ht.rehash();
//        ht.insert(5234258);
//        ht.insert(23155342);
//        ht.insert(582425234);
//        ht.insert(992334);
        System.out.println("After rehash and inserted more");
        System.out.println(ht);
        assertTrue(ht.search(5234258));
        assertTrue(ht.search(23155342));
        assertTrue(ht.search(582425234));
        assertTrue(ht.search(992334));
        ht.delete(992334);
        ht.delete(582425234);
        ht.delete(23155342);
        assertFalse(ht.search(992334));
        assertFalse(ht.search(582425234));
        assertFalse(ht.search(23155342));
        System.out.println("After big number insert delete and search");
        System.out.println(ht);
    }

    @Test
    public void fig513 () {
        HashFunction hf = new HashMod(10);
        HashTable ht = new HashQuadProbing (hf);
        System.out.println("Before rehash");
        ht.insert(15);
         ht.insert(18);
         ht.insert(49);
         ht.insert(58);
         ht.insert(69);
         ht.insert(1117788637);
         assertTrue(ht.search(1117788637));
         ht.insert(1119898723);
         assertTrue(ht.search(1119898723));
         System.out.println("Fig. 5.13");
         System.out.println(ht);
         ht.insert(26);
         ht.insert(72);
         ht.insert(95);
         System.out.println(ht);
         assertTrue(ht.search(69));
         ht.rehash();
         System.out.println("After rehash");
         System.out.println(ht);
         ht.insert(1117788637);
         ht.insert(537788637);
         ht.insert(347788637);
         ht.insert(347788637);
         ht.insert(987112637);
        System.out.println("duplicate key check");
        System.out.println(ht);
        ht.delete(347788637);
        ht.delete(1117788637);
        assertFalse(ht.search(347788637));
        assertFalse(ht.search(1117788637));


    }

    @Test
    public void fig518 () {

        HashFunction hf = new HashMod(10);
        HashFunction hf2 = new HashModThen(7, h -> 7 - h);
        HashTable ht = new HashDouble (hf, hf2);
        ht.insert(36);
        ht.insert(18);
        ht.insert(49);
        ht.insert(58);
        ht.insert(23);
        ht.insert(34);
        ht.search(89);
        ht.insert(1196725230);
        ht.insert(2938843);
        ht.insert(23125225);
        ht.delete(18);
        ht.delete(36);
        ht.delete(23);
        ht.delete(89);
        assertTrue(ht.search(23125225));
        assertTrue(ht.search(1196725230));
        assertFalse(ht.search(18));
        assertFalse(ht.search(89));
        assertFalse(ht.search(18));
        assertFalse(ht.search(23));
        System.out.println("Fig. 5.18");
        System.out.println(ht);
        ht.rehash();
        ht.insert(1342345);
        ht.insert(931225);
        ht.insert(78225225);
        ht.insert(1925225);
        System.out.println("After rehash and insertion of large numbers");
        System.out.println(ht);
        ht.delete(1342345);
        ht.delete(931225);
        ht.delete(78225225);
        ht.delete(1925225);
        assertFalse(ht.search(1925225));
        assertFalse(ht.search(78225225));
        assertFalse(ht.search(931225));
        assertFalse(ht.search(1342345));

    }

}