/*
 * @author Maahir Garg (Group 12A)
 */
class ServiceBegins extends Event {
  private Customer cust;
  private Counter counter;
  private Bank bank;

  public ServiceBegins(double time, Customer cust, Counter counter, Bank bank) {
    super(time);
    this.cust = cust;
    this.counter = counter;
    this.bank = bank;
  }

  @Override
  public String toString() {
    return super.toString() + ": " + cust.toString() + " " + cust.getTask().toString() + 
      " begin (by " + counter.toString() + ")";
  }

  @Override
  public Event[] simulate() {
    this.bank.setCounterAvailable(this.counter, false);
    double endTime = this.getTime() + this.cust.getServiceTime();
    return new Event[] {new ServiceEnds(endTime, this.cust, this.counter, this.bank)};
  }
}

