class ServiceBegins extends Event {
  private Customer cust;
  private Counter counter;

  public ServiceBegins(double time, Customer cust, Counter counter) {
    super(time);
    this.cust = cust;
    this.counter = counter;
  }

  @Override
  public String toString() {
    String str = String.format(": Customer %d service begin (by Counter %d)", 
        this.cust.getCustomerId(), this.counter.getCounterId());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    this.counter.setAvailable(false);
    double endTime = this.getTime() + this.cust.getServiceTime();
    return new Event[] {new ServiceEnds(endTime, this.cust, this.counter)};
  }
}

