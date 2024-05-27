/*
 * @author Maahir Garg (Group 12A)
 */
class Bank {
  private Counter[] counterList;
  private Queue customerQueue;

  public Bank(Counter[] counterList, Queue customerQueue) {
    this.counterList = counterList;
    this.customerQueue = customerQueue;
  }

  public Counter findAvailableCounter() {
    for (int i = 0; i < this.counterList.length; i++) {
      if (this.counterList[i].getAvailable()) {
        return this.counterList[i];
      }
    }
    return null;
  }

  public void setCounterAvailable(Counter counter, boolean value) {
    for (int i = 0; i < this.counterList.length; i++) {
      if (this.counterList[i] == counter) {
        this.counterList[i].setAvailable(value);
        return;
      }
    }
  }

  public boolean isQueueFull() {
    return this.customerQueue.isFull();
  }

  public Queue getQueue() {
    return this.customerQueue;
  }

  public Customer popQueue() {
    return (Customer) customerQueue.deq();
  }

  public boolean isCustomerWaiting() {
    return !(this.customerQueue.isEmpty());
  }

  public void addCustomerToQueue(Customer cust) {
    customerQueue.enq(cust);
  }

}

