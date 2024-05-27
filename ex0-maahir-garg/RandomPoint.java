import java.util.Random;

/**
 * CS2030S Exercise 0: RandomPoint.java
 * Semester 2, 2023/24
 *
 * <p>The Point class encapsulates a point on a 2D plane.
 *
 * @author maahir
 */

public class RandomPoint extends Point {
  private static int seed = 1;
  private static Random rng = new Random(RandomPoint.seed);


  public RandomPoint(double minX, double maxX, double minY, double maxY) {
    super((maxX-minX)*rng.nextDouble(), (maxY-minY)*rng.nextDouble());
  }


  public static void setSeed(int n) {
    RandomPoint.seed = n;
    RandomPoint.rng = new Random(RandomPoint.seed);
  }































}

