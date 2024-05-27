import java.util.Scanner;

/**
 * This class implements a bank simulation.
 *
 * @author Wei Tsang
 * @author Maahir Garg (Group 12A)
 * @version CS2030S AY23/24 Semester 2
 */ 
class BankSimulation extends Simulation {
  /** 
   * The availability of counters in the bank. 
   */
  private Seq<Counter> counterList;

  /** 
   * The list of customer arrival events to populate
   * the simulation with.
   */
  private Event[] initEvents;

  /** 
   * Constructor for a bank simulation. 
   *
   * @param sc A scanner to read the parameters from.  The first
   *           integer scanned is the number of customers; followed
   *           by the number of service counters.  Next is a 
   *           sequence of (arrival time, service time) pair, each
   *           pair represents a customer.
   */
  public BankSimulation(Scanner sc) {
    initEvents = new Event[sc.nextInt()];
    int numOfCounters = sc.nextInt();
    int counterQueueNum = sc.nextInt();
    int entranceQueueNum = sc.nextInt();

    counterList = new Seq<Counter>(numOfCounters);
    Queue<Customer> customerQueue = new Queue<Customer>(entranceQueueNum);

    counterList = new Seq<Counter>(numOfCounters);
    for (int i = 0; i < numOfCounters; i++) {
      counterList.set(i, new Counter(i, true, new Queue<Customer>(counterQueueNum)));
    }

    Bank bank = new Bank(counterList, customerQueue);

    int id = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      int task = sc.nextInt();
      int money = sc.nextInt();
      Task taskType;
      if (task == 0) {
        taskType = new Deposit(money);
      } else {
        taskType = new Withdrawal(money);
      }
      // initEvents[id] = new BankEvent(BankEvent.ARRIVAL, 
      //    arrivalTime, id, serviceTime, available);
      initEvents[id] = new Arrival(arrivalTime, new Customer(id, serviceTime, taskType), bank);
      id += 1;
    }
  }

  /**
   * Retrieve an array of events to populate the 
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }
}
