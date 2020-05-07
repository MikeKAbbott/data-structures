
import java.util.Random;
import java.util.function.Function;

// -------------------------------------------------------

abstract class HashFunction implements Function<Integer,Integer> {
     abstract int getBound ();
     abstract void setBound (int bound);

}

// -------------------------------------------------------

/**
 *
 * An instance of this class is created with a parameter 'bound'. Then
 * every time the hash function is applied to an argument 'x', it
 * returns the value of 'x' modulo the 'bound'. See the test cases in
 * HashFunctionTest for some examples.
 *
 */
 class HashMod extends HashFunction {
    public int bound;
    public HashMod(int bound){
        this.bound = bound;
    }
    public int getBound(){
        return this.bound;
    }
    public void setBound(int bound){
        this.bound = bound;
    }
    // complete the implementation and remove the abstract annotation
    public Integer apply(Integer key){
        return Math.floorMod(key,this.bound);
    }
}

// -------------------------------------------------------

/**
 *
 * An instance of this class is created with two parameters: a first
 * argument 'bound' that is used to create an instance of the
 * superclass, and a second argument 'after' of type
 * Function<Integer,Integer> that is used as follows. When the hash
 * function is invoked, the basic functionality of the super class is
 * first invoked, and then that result is given to the function
 * 'after'. The result of 'after' is also wrapped around so that it does not exceed bound.
 * See the test cases in HashFunctionTest for some examples.
 *
 */
class HashModThen extends HashMod {
    private int bound;
    public Function<Integer,Integer> after;
    HashModThen(int bound,Function<Integer,Integer> after){
        super(bound);
        this.after = after;
    }
    // complete the implementation and remove the abstract annotation
    public Integer apply(Integer key){
        int result = after.apply(super.apply(key));
        return Math.floorMod(result,getBound());
    }
}
// -------------------------------------------------------

/**
 *
 * An instance of this class is created with three parameters: a
 * random number generator 'r' of type Random and two integers 'p' and
 * 'm' where 'p' should be a prime number greater than or equal to
 * 'm'. Using the random number generator, the constructor generates
 * two random numbers 'a' and 'b' such that 'a' is in the range 1
 * (inclusive) and p (exclusive) and 'b' is in the range 0 (inclusive)
 * and p (exclusive). The resulting hash function should be a
 * universal hash function as explained in the book. Again see the
 * test cases in HashFunctionTest for some examples.
 *
 */
//look at book
class HashUniversal extends HashFunction {
    // complete the implementation and remove the abstract annotation
    private Random r;
    private int p;
    private int m;
    private int a;
    private int b;
    HashUniversal(Random r, Integer p, Integer m){
        this.r = r;
        this.p = p;
        this.m = m;
        this.a = r.nextInt(p - 1) + 1;
        this.b = r.nextInt(p);
    }
    public int getBound(){
        return m;
    }
    public void setBound(int bound){
        this.m = bound;
    }
    public Integer apply(Integer key){
        return  Math.floorMod(Math.floorMod(((a * key + b)), p),m);
    }
}

// -------------------------------------------------------
// -------------------------------------------------------
