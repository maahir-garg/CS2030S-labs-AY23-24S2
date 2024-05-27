class JoinCounterQueue extends Event {
  private Customer cust;
  private Bank bank;
  private Counter counter;

  public JoinCounterQueue(double time, Customer cust, Counter counter, Bank bank) {
    super(time);
    this.cust = cust;
    this.bank = bank;
    this.counter = counter;
  }

  @Override
  public String toString() { 
    return super.toString() + ": " + this.cust.toString() + 
      " joined counter queue (at " + this.counter.toString() + ")";
  }

  @Override
  public Event[] simulate() {
    this.counter.addCustomerToQueue(cust);
    return new Event[] {};
  }
}
