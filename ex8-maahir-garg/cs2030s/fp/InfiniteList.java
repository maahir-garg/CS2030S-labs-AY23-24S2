package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class InfiniteList<T> {

  private static class Sentinel extends InfiniteList<Object> {

    @Override
    public String toString() {
      return "-";
    }

    @Override
    public boolean isSentinel() {
      return true;
    }

    @Override
    public Object head() {
      throw new NoSuchElementException();
    }

    @Override
    public InfiniteList<Object> tail() {
      return this;
    }

    @Override
    public <R> InfiniteList<R> map(Transformer<? super Object, ? extends R> mapper) {
      return InfiniteList.sentinel();
    }

    @Override
    public InfiniteList<Object> filter(BooleanCondition<? super Object> predicate) {
      return this;
    }

    @Override
    public InfiniteList<Object> limit(long n) {
      return this;
    }

    @Override
    public List<Object> toList() {
      return new ArrayList<>();
    }

    @Override
    public InfiniteList<Object> takeWhile(BooleanCondition<? super Object> predicate) {
      return this;
    }

    @Override
    public <U> U reduce(U identity, Combiner<U, ? super Object, U> accumulator) {
      return identity;
    }

  }
  
  private final Lazy<Maybe<T>> head;
  private final Lazy<InfiniteList<T>> tail;
  private static final InfiniteList<?> SENTINEL = new Sentinel();

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
    this.head = Lazy.of(Maybe.some(head));
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
    @SuppressWarnings("unchecked")
    InfiniteList<T> temp = (InfiniteList<T>) SENTINEL;
    return temp;
  }

  public InfiniteList<T> limit(long n) {
    // TODO
    if (n <= 0) {
      return InfiniteList.sentinel();
    }
    return new InfiniteList<>(
        this.head,
        this.tail.map(t -> this.head.get().map(x -> t.limit(n - 1)).orElseGet(() -> t.limit(n)))
        );
  }

  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    // TODO
    
    Lazy<Maybe<T>> filterHead = Lazy.of(() -> Maybe.some(this.head()).filter(predicate));
    return new InfiniteList<>(
        filterHead,
        Lazy.of(() -> filterHead.get()
          .map(x -> this.tail().takeWhile(predicate)).orElse(InfiniteList.sentinel()))
        );
  }

  public boolean isSentinel() {
    return false;
  }

  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    // TODO
    return accumulator.combine(this.tail().reduce(identity, accumulator), this.head());
  }

  public long count() {
    // TODO
    return this.toList().size();
  }

  public List<T> toList() {
    // TODO
    List<T> temp = this.tail.get().toList();
    this.head.get().ifPresent(x -> temp.add(0, x));
    return temp;
  }

  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }
}
