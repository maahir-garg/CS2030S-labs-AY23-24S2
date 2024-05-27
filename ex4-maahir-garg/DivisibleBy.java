/**
 * A boolean condition with an integer parameter y that can be 
 * apply to another integer x.  Returns true if x is divisible 
 * by y, false otherwise.
 * CS2030S Exercise 4
 * AY23/24 Semester 2
 *
 * @author Maahir Garg (12A)
 */

public class DivisibleBy implements BooleanCondition<Integer> {
  private Integer divideBy;

  public DivisibleBy(Integer divideBy) {
    this.divideBy = divideBy;
  }

  @Override
  public boolean test(Integer integer) {
    return (integer % this.divideBy == 0);
  }

}

