package cs2030s.fp;

public class Lazy<T> {
  private Producer<? extends T> producer;
  private Maybe<T> value;

  private Lazy(Maybe<T> value) {
    this.value = value;
  }

  private Lazy(Producer<? extends T> producer) {
    this.producer = producer;
    this.value = Maybe.none();
  }

  public static <T> Lazy<T> of(T v) {
    return new Lazy<T>(Maybe.<T>some(v));
  }

  public static <T> Lazy<T> of(Producer<? extends T> s) {
    return new Lazy<T>(s);
  }

  public T get() {
    T temp = this.value.orElseGet(producer);
    this.value = Maybe.of(temp);
    return temp;
  }

  @Override
  public String toString() {
    return this.value.map(x -> String.valueOf(x)).orElse("?");
  }

  
  public <U> Lazy<U> map(Transformer<? super T, ? extends U> transformer) {
    return Lazy.of(() -> transformer.transform(this.get()));
  }

  
  public <U> Lazy<U> flatMap(Transformer<? super T, ? extends Lazy<? extends U>> transformer) {
    return Lazy.of(() -> transformer.transform(this.get()).get());
  }
 
  public Lazy<Boolean> filter(BooleanCondition<? super T> cond) {
    return Lazy.of(() -> cond.test(this.get()));
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (o instanceof Lazy<?>) {
      Lazy<?> temp = (Lazy<?>) o;
      return (this.get().equals(temp.get()));
    }

    return false;
  }

  public <S, R> Lazy<R> combine(Lazy<S> other, 
      Combiner<? super T, ? super S, ? extends R> combiner) {
    return Lazy.of(() -> combiner.combine(this.get(), other.get()));
  }

}
