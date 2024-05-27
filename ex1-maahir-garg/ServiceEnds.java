class ServiceEnds extends Event {
  private Customer cust;
  private Counter counter;

  public ServiceEnds(double time, Customer cust, Counter counter) {
    super(time);
    this.cust = cust;
    this.counter = counter;
  }

  @Override 
  public String toString() {
    String str = String.format(": Customer %d service done (by Counter %d)",
        this.cust.getCustomerId(), this.counter.getCounterId());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    this.counter.setAvailable(true);
    return new Event[] {new Departure(this.getTime(), this.cust)};
  }
}
