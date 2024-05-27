class Arrival extends Event {
  
  private Customer cust;
  private Counter[] counterList;

  public Arrival(double time, Customer cust, Counter[] counterList) {

    super(time);
    this.cust = cust;
    this.counterList = counterList;
  }

  @Override 
  public String toString() {
    String str = String.format(": Customer %d arrives", this.cust.getCustomerId());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    int counter = -1;
    for (int i = 0; i < this.counterList.length; i++) {
      if (this.counterList[i].getAvailable()) {
        counter = i;
        break;
      }
    }

    if (counter  == -1) {
      return new Event[] {new Departure(this.getTime(), this.cust)};
    } else {
      return new Event[] {new ServiceBegins(this.getTime(), this.cust, this.counterList[counter])};
    }
  }
}

 
