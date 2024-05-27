/**
 * A boolean condition with parameter x that can be applied to
 * a string.  Returns true if the string is longer than x; false
 * otherwise.
 * CS2030S Exercise 4
 * AY23/24 Semester 2
 *
 * @author Maahir Garg (12A)
 */

public class LongerThan implements BooleanCondition<String> {

  private Integer length;

  public LongerThan(Integer length) {
    this.length = length;
  }

  @Override
  public boolean test(String str) {
    return (str.length() > this.length);
  }
}

