/**
 * The Transformer interface that can transform a type T
 * to type U.
 * CS2030S Exercise 4
 * AY23/24 Semester 2
 *
 * @author Maahir Garg (12A)
 */

public interface Transformer<T, U> {
  U transform(T t);
}


