/*
 * @author Maahir Garg (Group 12A)
 */
class Counter {
  private int counterId;
  private boolean available;

  public Counter(int counterId, boolean available) {
    this.counterId = counterId;
    this.available = available;
  }

  @Override
  public String toString() {
    return "S" + this.counterId;
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

