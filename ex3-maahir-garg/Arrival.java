/*
 *@author: Maahir Garg(12A)
 */
class Arrival extends Event {
  
  private Customer cust;
  private Bank bank;

  public Arrival(double time, Customer cust, Bank bank) {
    super(time);
    this.cust = cust;
    this.bank = bank;
  }

  @Override 
  public String toString() {
    return super.toString() + ": " + cust.toString() + " arrived " + bank.printQueue();
  }

  @Override
  public Event[] simulate() {
    Counter availableCounter = bank.findAvailableCounter();
    if (availableCounter == null) {
      Counter availableQueueAtCounter = this.bank.findAvailableQueueAtCounter();
      if (availableQueueAtCounter == null) {
        if (bank.isQueueFull()) {
          return new Event[] {new Departure(this.getTime(), this.cust)};
        } else {
          return new Event[] {new JoinQueue(this.getTime(), this.cust, this.bank)};
        }
      } else {
        return new Event[] {new JoinCounterQueue(this.getTime(), this.cust, 
            availableQueueAtCounter, this.bank)};
      }
    } else {
      return new Event[] {new ServiceBegins(this.getTime(), this.cust, 
          availableCounter, this.bank)};
    }
  }
}

 
