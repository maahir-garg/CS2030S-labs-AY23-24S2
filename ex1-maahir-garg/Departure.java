class Departure extends Event {
  private Customer cust;

  public Departure(double time, Customer cust) {
    super(time);
    this.cust = cust;
  }

  @Override
  public String toString() {
    String str = String.format(": Customer %d departed", this.cust.getCustomerId());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    return new Event[] {};
  }
}

