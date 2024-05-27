/*
 * @author Maahir Garg (Group 12A)
 */
class Counter implements Comparable<Counter> {
  private int counterId;
  private boolean available;
  private int money = 100;
  private Queue<Customer> customerQueue;

  public Counter(int counterId, boolean available, Queue<Customer> customerQueue) {
    this.counterId = counterId;
    this.available = available;
    this.customerQueue = customerQueue;
  }

  @Override
  public String toString() {
    return "S" + this.counterId + " $" + this.money + " " + this.customerQueue.toString();
  }

  @Override
  public int compareTo(Counter counterToCompare) {
    Integer thisQueueLength = this.getQueueLength();
    Integer otherQueueLength = counterToCompare.getQueueLength();
    int status = thisQueueLength.compareTo(otherQueueLength);
    if (status == 0) {
      if (this.getCounterId() < counterToCompare.getCounterId()) {
        return -1;
      } else {
        return 1;
      }
    } else {
      return status;
    }
  }

  public int getCounterId() {
    return this.counterId;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }

  public boolean getAvailable() {
    return this.available;
  }

  public int getMoney() {
    return this.money;
  }

  public boolean updateMoney(int money) {
    if (this.money + money >= 0) {
      this.money += money;
      return true;
    } else {
      return false;
    }
  }


  public int getQueueLength() {
    return this.customerQueue.length();
  }

  public boolean isQueueFull() {
    return this.customerQueue.isFull();
  }
  
  public Customer popQueue() {
    return customerQueue.deq();
  }
  
  public void addCustomerToQueue(Customer cust) {
    customerQueue.enq(cust);
  }
  
  public boolean isCustomerWaiting() {
    return !(this.customerQueue.isEmpty());
  }

}

