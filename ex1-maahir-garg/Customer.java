class Customer {

  private int customerId;
  private double serviceTime;

  public Customer(int customerId, double serviceTime) {
    this.customerId = customerId;
    this.serviceTime = serviceTime;
  }

  public int getCustomerId() {
    return this.customerId;
  }

  public double getServiceTime() {
    return this.serviceTime;
  }
}
