/**
 * Takes an item and return the item in a box.
 * CS2030S Exercise 4
 * AY23/24 Semester 2
 *
 * @author Maahir Garg (12A)
 */

public class BoxIt<T> implements Transformer<T, Box<T>> {

  @Override
  public Box<T> transform(T t) {
    return Box.ofNullable(t);
  }

}

