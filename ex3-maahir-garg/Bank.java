/*
 * @author Maahir Garg (Group 12A)
 */
class Bank {
  private Seq<Counter> counterList;
  private Queue<Customer> customerQueue;

  public Bank(Seq<Counter> counterList, Queue<Customer> customerQueue) {
    this.counterList = counterList;
    this.customerQueue = customerQueue;
  }

  public Counter findAvailableCounter() {
    for (int i = 0; i < this.counterList.getLength(); i++) {
      if (this.counterList.get(i).getAvailable()) {
        return this.counterList.get(i);
      }
    }
    return null;
  }

  public void setCounterAvailable(Counter counter, boolean value) {
    for (int i = 0; i < this.counterList.getLength(); i++) {
      if (this.counterList.get(i) == counter) {
        this.counterList.get(i).setAvailable(value);
        return;
      }
    }
  }

  public Counter findAvailableQueueAtCounter() {
    Counter counter = counterList.min();
    if (counter.isQueueFull()) {
      return null;
    } else {
      return counter;
    }
  }

  public String printQueue() {
    return this.customerQueue.toString();
  }

  public boolean isQueueFull() {
    return this.customerQueue.isFull();
  }

  public Customer popQueue() {
    return customerQueue.deq();
  }

  public boolean isCustomerWaiting() {
    return !(this.customerQueue.isEmpty());
  }

  public void addCustomerToQueue(Customer cust) {
    customerQueue.enq(cust);
  }

}

