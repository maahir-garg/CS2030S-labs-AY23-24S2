/*
 * @author Maahir Garg (Group 12A)
 */
class Customer {

  private int customerId;
  private double serviceTime;
  private Task task;

  public Customer(int customerId, double serviceTime, Task task) {
    this.customerId = customerId;
    this.serviceTime = serviceTime;
    this.task = task;
  }

  @Override
  public String toString() {
    return "C" + this.customerId;
  }

  public int getCustomerId() {
    return this.customerId;
  }

  public double getServiceTime() {
    return this.serviceTime;
  }

  public Task getTask() {
    return this.task;
  }

  public int getMoney() {
    return this.task.getMoney();
  }
}
