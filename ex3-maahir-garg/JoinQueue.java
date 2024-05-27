/*
 * @author Maahir Garg (Group 12A)
 */
class JoinQueue extends Event {

  private Customer cust;
  private Bank bank;

  public JoinQueue(double time, Customer cust, Bank bank) {
    super(time);
    this.cust = cust;
    this.bank = bank;
  }

  @Override
  public String toString() {
    return super.toString() + ": " + cust.toString() + 
      " joined bank queue " + bank.printQueue();
  }

  @Override
  public Event[] simulate() {
    this.bank.addCustomerToQueue(this.cust);
    return new Event[] {};
  }

}
