class Counter {
  private int counterId;
  private boolean available;

  public Counter(int counterId, boolean available) {
    this.counterId = counterId;
    this.available = available;
  }

  public int getCounterId() {
    return this.counterId;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }

  public boolean getAvailable() {
    return this.available;
  }
}

