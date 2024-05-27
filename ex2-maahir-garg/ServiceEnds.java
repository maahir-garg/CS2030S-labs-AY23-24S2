/*
 * @author Maahir Garg (Group 12A)
 */
class ServiceEnds extends Event {
  private Customer cust;
  private Counter counter;
  private Bank bank;

  public ServiceEnds(double time, Customer cust, Counter counter, Bank bank) {
    super(time);
    this.cust = cust;
    this.counter = counter;
    this.bank = bank;
  }

  @Override 
  public String toString() {
    return super.toString() + ": " + cust.toString() + " " + cust.getTask().toString() + 
      " done (by " + counter.toString() + ")";
  }

  @Override
  public Event[] simulate() {
    this.bank.setCounterAvailable(this.counter, true);
    Event depart = new Departure(this.getTime(), this.cust);
    if (bank.isCustomerWaiting()) {
      return new Event[] {depart, new ServiceBegins(this.getTime(), 
          this.bank.popQueue(), this.counter, this.bank)};
    } else {
      return new Event[] {depart};
    }
  }

}
