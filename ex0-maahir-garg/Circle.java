/**
 * CS2030S Exercise 0: Circle.java
 * Semester 2, 2023/24
 *
 * <p>The Circle class represents a circle with a center 
 * and a radius.
 *
 * @author maahir
 */
class Circle {
  /** The center of the circle. */
  private Point c;

  /** The radius of the circle (assume positive). */
  private double r;

  /**
   * Constructor for a circle.  Takes in a center c and a 
   * radius r (assume to be positive). 
   *
   * @param c The center of the new circle.
   * @param r The radius of the new circle.
   */
  public Circle(Point c, double r) {
    this.c = c;
    this.r = r;
  }

  /**
   * Checks if a given point p is contained within the circle.
   *
   * @param p The point to test.
   * @return true if p is within this circle; false otherwise.
   */
  public boolean contains(Point p) {
    return (Math.pow((p.getX()-c.getX()), 2) + Math.pow((p.getY()-c.getY()), 2)<= this.r*this.r);
  }

  /**
   * Return the string representation of this circle.
   *
   * @return The string representing of this circle.
   */
  public String toString() {
    return "{ center: " + this.c + ", radius: " + this.r + " }";
  }
}
