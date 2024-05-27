package cs2030s.fp;

import java.util.List;

public class InfiniteList<T> {

  private final Lazy<Maybe<T>> head;
  private final Lazy<InfiniteList<T>> tail;

  private InfiniteList() { 
    this.head = null; 
    this.tail = null;
  }

  public static <T> InfiniteList<T> generate(Producer<? extends T> producer) {
    return new InfiniteList<T>(Lazy.of(() -> Maybe.some(producer.produce())), 
        Lazy.of(() -> InfiniteList.generate(producer)));
  }

  public static <T> InfiniteList<T> iterate(T seed, Transformer<? super T, ? extends T> next) {
    return new InfiniteList<T>(seed, () -> InfiniteList.iterate(next.transform(seed), next));
  }

  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    this.head = Lazy.of(() -> Maybe.some(head));
    this.tail = Lazy.of(tail);
  }

  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  public T head() {
    return this.head.get().orElseGet(() -> this.tail.get().head());
  }

  public InfiniteList<T> tail() {
    return this.head.get().map(x -> this.tail.get()).orElseGet(() -> this.tail.get().tail());
  }

  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    return new InfiniteList<>(
        Lazy.of(() -> this.head.get().map(mapper)), 
        Lazy.of(() -> this.tail.get().map(mapper))
        );
  }

  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    return new InfiniteList<>(
        Lazy.of(() -> this.head.get().filter(predicate)), 
        Lazy.of(() -> this.tail.get().filter(predicate))
        );
  }

  public static <T> InfiniteList<T> sentinel() {
    // TODO
    return new InfiniteList<>();
  }

  public InfiniteList<T> limit(long n) {
    // TODO
    return new InfiniteList<>();
  }

  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    // TODO
    return new InfiniteList<>();
  }

  public boolean isSentinel() {
    return false;
  }

  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    // TODO
    return null;
  }

  public long count() {
    // TODO
    return 0;
  }

  public List<T> toList() {
    // TODO
    return List.of();
  }

  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }
}
