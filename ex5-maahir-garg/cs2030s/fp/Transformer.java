package cs2030s.fp;

/**
 * CS2030S Exercise 5
 * AY24/24 Semester 2
 *
 * @author Maahir Garg (12A)
 */

public interface Transformer<T, U> {
  U transform(T t);
}
