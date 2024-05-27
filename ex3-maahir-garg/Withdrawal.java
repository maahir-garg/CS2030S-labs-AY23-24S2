/*
 * @author Maahir Garg (Group 12A)
 */
class Withdrawal extends Task {
  private int money;

  public Withdrawal(int money) {
    this.money = money;
  }

  
  @Override
  public int getTask() {
    return 1;
  } 
  
  @Override
  public String toString() {
    return "withdrawal $" + this.money;
  }

  public int getMoney() {
    return -1 * this.money;
  }
} 
