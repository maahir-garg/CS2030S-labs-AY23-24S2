/*
 * @author Maahir Garg (Group 12A)
 */
class Deposit extends Task {
  private int money;

  public Deposit(int money) {
    this.money = money;
  }

  @Override
  public int getTask() {
    return 0;
  }

  @Override
  public String toString() {
    return "deposit $" + this.money;
  }

  public int getMoney() {
    return this.money;
  }
}
