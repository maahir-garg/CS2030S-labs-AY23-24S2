/*
 * @author Maahir Garg (Group 12A)
 */
class Departure extends Event {
  private Customer cust;

  public Departure(double time, Customer cust) {
    super(time);
    this.cust = cust;
  }

  @Override
  public String toString() {
    return super.toString() + ": " + cust.toString() + " departed";
  }

  @Override
  public Event[] simulate() {
    return new Event[] {};
  }
}

