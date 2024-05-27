package cs2030s.fp;

import java.util.NoSuchElementException;

/**
 * CS2030S Lab 5
 * AY23/24 Semester 2
 *
 * @author Maahir Garg (12A)
 */

public abstract class Maybe<T> {
  private static Maybe<?> NONE = new None();

  protected abstract T get() throws NoSuchElementException;

  public static <T> Maybe<T> none() {
    @SuppressWarnings("unchecked")
    Maybe<T> temp = (Maybe<T>) NONE;
    return temp;
  }

  public static <T> Maybe<T> some(T t) {
    return new Some<T>(t);
  }

  public static <T> Maybe<T> of(T t) {
    if (t == null) {
      return none();
    }

    return some(t);
  }

  public abstract Maybe<T> filter(BooleanCondition<? super T> con);

  public abstract <U> Maybe<U> map(Transformer<? super T, ? extends U> transformer);

  public abstract <U> Maybe<U> flatMap(Transformer<? super T, 
      ? extends Maybe<? extends U>> transformer);

  public abstract T orElse(T t);

  public abstract T orElseGet(Producer<? extends T> producer);

  public abstract void ifPresent(Consumer<? super T> consumer);


  private static class None extends Maybe<Object> {

    @Override
    public String toString() {
      return "[]";
    }

    @Override
    public boolean equals(Object o) {
      return o instanceof None;
    }

    @Override
    protected Object get() throws NoSuchElementException {
      throw new NoSuchElementException();
    }

    @Override
    public Maybe<Object> filter(BooleanCondition<? super Object> cond) {
      return Maybe.none();
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super Object, ? extends U> transfomer) {
      return Maybe.none();
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super Object, 
        ? extends Maybe<? extends U>> transformer) {
      return Maybe.none();
    }

    @Override
    public Object orElse(Object t) {
      return t;
    }

    @Override
    public Object orElseGet(Producer<? extends Object> producer) {
      return producer.produce();
    }

    @Override
    public void ifPresent(Consumer<? super Object> consumer) {
      return;
    }
  }

  private static final class Some<T> extends Maybe<T> {
    private final T content;

    private Some(T t) {
      this.content = t;
    }

    @Override
    public String toString() {
      return "[" + this.content + "]";
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Some<?>) {
        Some<?> temp = (Some<?>) o;
        if (this.content == temp.get()) {
          return true;
        }
        if (this.content == null || temp.get() == null) {
          return false;
        }
        return this.content.equals(temp.get());
      }
      return false;
    }

    @Override
    protected T get() throws NoSuchElementException {
      return this.content;
    }

    @Override
    public Maybe<T> filter(BooleanCondition<? super T> con) {
      if (this.content == null) {
        return this;
      }
      if (con.test(this.content)) {
        return this;
      }
      return Maybe.<T>none();
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super T, ? extends U> transformer) {
      return Maybe.<U>some(transformer.transform(this.content));
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> transformer) {
      @SuppressWarnings("unchecked")
      Maybe<U> temp = (Maybe<U>) transformer.transform(this.content);
      return temp;
    }

    @Override
    public T orElse(T t) {
      return this.content;
    }

    @Override
    public T orElseGet(Producer<? extends T> producer) {
      return this.content;
    }

    @Override
    public void ifPresent(Consumer<? super T> consumer) {
      consumer.consume(this.content);
      return;
    }
  }
}
