/*
 * @author Maahir Garg (Group 12A)
 */
class ServiceEnds extends Event {
  private Customer cust;
  private Counter counter;
  private Bank bank;
  private boolean success;

  public ServiceEnds(double time, Customer cust, Counter counter, Bank bank, boolean success) {
    super(time);
    this.cust = cust;
    this.counter = counter;
    this.bank = bank;
    this.success = success;
  }

  @Override 
  public String toString() {
    String status;
    if (success) {
      status = "success";
    } else {
      status = "fail";
    }
    return super.toString() + ": " + cust.toString() + " " + cust.getTask().toString() + 
      " done (by " + counter.toString() + ") " + status;
  }

  @Override
  public Event[] simulate() {
    this.bank.setCounterAvailable(this.counter, true);
    Event depart = new Departure(this.getTime(), this.cust);
    if (this.counter.isCustomerWaiting()) {
      Event counterQueueToCounter = new ServiceBegins(this.getTime(),
          this.counter.popQueue(), this.counter, this.bank);
      if (this.bank.isCustomerWaiting()) {
        return new Event[] {depart, counterQueueToCounter, 
          new JoinCounterQueue(this.getTime() + 0.01, this.bank.popQueue(),
              this.counter, this.bank)};
      } else {
        return new Event[] {depart, counterQueueToCounter};
      }
    } else {
      if (this.bank.isCustomerWaiting()) {
        return new Event[] {depart, new ServiceBegins(this.getTime(), this.bank.popQueue(),
            this.counter, this.bank)};
      } else {
        return new Event[] {depart};
      }
    }
  }
}
