/**
 * A transformer with a parameter k that takes in an object x 
 * and outputs the last k digits of the hash value of x.
 * CS2030S Exercise 4
 * AY23/24 Semester 2
 *
 * @author Maahir Garg (12A)
 */


public class LastDigitsOfHashCode implements Transformer<Object, Integer> {

  private int k;

  public LastDigitsOfHashCode(int k) {
    this.k = k;
  }

  @Override
  public Integer transform(Object t) {
    int hashCode = t.hashCode();
    return Math.abs(hashCode % (int) Math.pow(10, k));
  }
}

