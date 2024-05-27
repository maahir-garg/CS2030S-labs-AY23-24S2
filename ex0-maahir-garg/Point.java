/**
 * CS2030S Exercise 0: Point.java
 * Semester 2, 2023/24
 *
 * <p>The Point class encapsulates a point on a 2D plane.
 *
 * @author maahir
 */
class Point {
  private double x;
  private double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return "(" + String.valueOf(this.x) + ", " + String.valueOf(this.y) + ")";
  }

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }
}
